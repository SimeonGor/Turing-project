package com.example.turing_project.service;

import com.example.turing_project.entity.Answer;
import org.springframework.stereotype.Service;

@Service
public class BasicTuringServiceImpl implements TuringService {
    @Override
    public Answer handle(String request) {
        Answer answer = new Answer();
        answer.setText("It's answer to %s".formatted(request));
        answer.setType("text");
        return answer;
    }


}
