package com.example.turing_project.service;

import com.example.turing_project.dto.AnswerDto;
import com.example.turing_project.entity.Answer;
import org.springframework.stereotype.Service;

@Service
public class BasicTuringServiceImpl implements TuringService {
    @Override
    public AnswerDto handle(String request) {
        return AnswerDto.builder()
                .text("It's answer to %s".formatted(request))
                .build();
    }


}
