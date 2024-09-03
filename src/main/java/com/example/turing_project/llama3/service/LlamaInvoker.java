package com.example.turing_project.llama3.service;

import com.example.turing_project.llama3.config.LlamaProperties;
import com.example.turing_project.llama3.dto.MessageRequest;
import com.example.turing_project.llama3.dto.MessageResponse;
import com.example.turing_project.llama3.exceptions.InvalidLlamaRequestException;
import com.example.turing_project.llama3.exceptions.NullResponseException;
import com.example.turing_project.service.LLMInvoker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
public class LlamaInvoker implements LLMInvoker {
    private final RestClient restClient;

    public LlamaInvoker(LlamaProperties properties) {
        restClient = RestClient.builder()
                .baseUrl(properties.getUrl())
                .build();
    }

    @Override
    public void init() {

    }

    @Override
    public String invoke(String question) {
        log.info("send question to Llama3");

        MessageRequest requestBody = MessageRequest.builder()
                .messages(List.of(
                        MessageRequest.Message.builder()
                                .content(question)
                                .build()
                ))
                .build();

        MessageResponse messageResponse = restClient.post()
                .uri("/api/chat")
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                .body(requestBody)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new InvalidLlamaRequestException(
                            "Request was handled with %d status code".formatted(response.getStatusCode().value())
                    );
                })
                .body(MessageResponse.class);

        if (messageResponse == null) {
            throw new NullResponseException("GigaChat response cannot be null");
        }

        return messageResponse.getContent();
    }
}
