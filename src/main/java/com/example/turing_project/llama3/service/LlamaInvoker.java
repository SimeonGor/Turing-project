package com.example.turing_project.llama3.service;

import com.example.turing_project.llama3.dto.MessageRequest;
import com.example.turing_project.llama3.dto.MessageResponse;
import com.example.turing_project.service.LLMInvoker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;

import java.util.List;

@Slf4j
public class LlamaInvoker implements LLMInvoker {

    @Override
    public void init() {

    }

    @Override
    public String invoke(String question) {
//        log.info("send question to Llama3");
//
//        MessageRequest body = MessageRequest.builder()
//                .messages(List.of(
//                        MessageRequest.Message.builder()
//                                .content(question)
//                                .build()
//                ))
//                .build();
//
//        MessageResponse messageResponse = restClient.post()
//                .uri("https://gigachat.devices.sberbank.ru/api/v1/chat/completions")
//                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                .header("Accept", MediaType.APPLICATION_JSON_VALUE)
//                .header("Authorization", accessToken)
//                .body(body)
//                .retrieve()
//                .onStatus(HttpStatusCode::isError, (request, response) -> {
//                    throw new InvalidGigaChatRequestException(
//                            "Request was handled with %d status code".formatted(response.getStatusCode().value())
//                    );
//                })
//                .body(MessageResponse.class);
//
//        if (messageResponse == null) {
//            throw new NullResponseException("GigaChat response cannot be null");
//        }
//
//        return messageResponse.getContent();
        return null;
    }
}
