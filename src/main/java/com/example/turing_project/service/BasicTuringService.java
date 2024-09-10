package com.example.turing_project.service;

import com.example.turing_project.dto.AnswerDto;
import com.example.turing_project.dto.HistoryContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class BasicTuringService implements TuringService {
    private LLMInvoker llmInvoker;

    @Override
    public Long getHistoryContextLimits() {
        return llmInvoker.getHistoryContextLimits();
    }

    @Override
    public AnswerDto handle(String request, HistoryContext historyContext) {
        log.info("Обработка запроса {}", request);
        String result = llmInvoker.invoke(request, historyContext);
        return AnswerDto.builder()
                .text(result)
                .build();
    }


}
