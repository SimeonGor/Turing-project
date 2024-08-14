package com.example.turing_project.service;

import com.example.turing_project.dto.AnswerDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasicTuringService implements TuringService {
    @Override
    public AnswerDto handle(String request) {
        log.info("Запрос {} перенаправлен в GigaChat", request);
        return AnswerDto.builder()
                .text("It's answer to %s".formatted(request))
                .build();
    }


}
