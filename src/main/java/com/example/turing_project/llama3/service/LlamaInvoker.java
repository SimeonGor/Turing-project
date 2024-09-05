package com.example.turing_project.llama3.service;

import com.example.turing_project.dto.HistoryContext;
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
import java.util.stream.Stream;

@Slf4j
public class LlamaInvoker implements LLMInvoker {
    private final RestClient restClient;
    private final Long contextLimits;

    public LlamaInvoker(LlamaProperties properties) {
        this.contextLimits = properties.getContextLimits();

        restClient = RestClient.builder()
                .baseUrl(properties.getUrl())
                .build();
    }

    @Override
    public void init() {

    }

    @Override
    public Long getHistoryContextLimits() {
        return contextLimits;
    }

    @Override
    public String invoke(String question, HistoryContext historyContext) {
        log.info("send question to Llama3");

        List<MessageRequest.Message> messages = new java.util.ArrayList<>(historyContext.getMessages().stream().flatMap(
                messageDto -> Stream.of(
                        MessageRequest.Message.builder().role("user").content(messageDto.getQuestion().getText()).build(),
                        MessageRequest.Message.builder().role("assistant").content(messageDto.getAnswer().getText()).build())).toList());

        messages.add(MessageRequest.Message.builder().content(question).build());

        MessageRequest requestBody = MessageRequest.builder()
                .messages(messages)
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
            throw new NullResponseException("Llama response cannot be null");
        }

        return messageResponse.getContent();
    }
}
