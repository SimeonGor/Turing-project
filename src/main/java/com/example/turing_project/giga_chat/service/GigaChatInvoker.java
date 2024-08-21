package com.example.turing_project.giga_chat.service;

import com.example.turing_project.giga_chat.config.GigaChatProperties;
import com.example.turing_project.giga_chat.dto.MessageRequest;
import com.example.turing_project.giga_chat.dto.MessageResponse;
import com.example.turing_project.giga_chat.dto.TokenResponse;
import com.example.turing_project.giga_chat.exceptions.InvalidGigaChatRequestException;
import com.example.turing_project.giga_chat.exceptions.NullResponseException;
import com.example.turing_project.service.LLMInvoker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
public class GigaChatInvoker implements LLMInvoker {
    private final String authorizationData;
    private final String scope;
    private LocalDateTime tokenExpiredAt;
    private String accessToken;
    private final RestClient restClient;

    public GigaChatInvoker(GigaChatProperties properties) {
        this.scope = properties.getScope();

        String raw_authorization_data = "%s:%s".formatted(properties.getClientId(), properties.getClientSecret());

        this.authorizationData = new String(Base64.getEncoder()
                .encode(raw_authorization_data.getBytes()));

        restClient = RestClient.builder()
                .baseUrl(properties.getUrl())
                .build();
    }

    @Override
    public void init() {
        updateAccessToken();
    }

    @Override
    public String invoke(String question) {
        log.info("send question to GigaChat");

        if (isTokenExpired()) {
            updateAccessToken();
        }

        MessageRequest body = MessageRequest.builder()
                .messages(List.of(
                        MessageRequest.Message.builder()
                                .content(question)
                                .build()
                ))
                .build();

        MessageResponse messageResponse = restClient.post()
                .uri("https://gigachat.devices.sberbank.ru/api/v1/chat/completions")
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", accessToken)
                .body(body)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new InvalidGigaChatRequestException(
                            "Request was handled with %d status code".formatted(response.getStatusCode().value())
                    );
                })
                .body(MessageResponse.class);

        if (messageResponse == null) {
            throw new NullResponseException("GigaChat response cannot be null");
        }

        return messageResponse.getContent();
    }

    public void updateAccessToken() {
        log.info("request access token");

        String authorizationHeader = "Basic %s".formatted(authorizationData);
        String body = "scope=%s".formatted(scope);

        TokenResponse tokenResponse = restClient.post()
                .uri("https://ngw.devices.sberbank.ru:9443/api/v2/oauth")
                .header("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                .header("RqUID", UUID.randomUUID().toString())
                .header("Authorization", authorizationHeader)
                .body(body)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new InvalidGigaChatRequestException(
                            "Request was handled with %d status code".formatted(response.getStatusCode().value())
                    );
                })
                .body(TokenResponse.class);

        if (tokenResponse == null) {
            throw new NullResponseException("GigaChat response cannot be null");
        }
        accessToken = "Bearer %s".formatted(tokenResponse.getAccess_token());
        tokenExpiredAt = tokenResponse.getExpired_at();

        log.trace("receive access token");
    }

    public boolean isTokenExpired() {
        return LocalDateTime.now().isAfter(tokenExpiredAt);
    }
}
