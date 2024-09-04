package com.example.turing_project.service;

import com.example.turing_project.dto.AnswerDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class BasicTuringService implements TuringService {
    private LLMInvoker llmInvoker;
    @Override
    public AnswerDto handle(String request) {
        log.info("Обработка запроса {}", request);
        String result = llmInvoker.invoke(request);
        return AnswerDto.builder()
                .text(result)
                .build();
    }


}
