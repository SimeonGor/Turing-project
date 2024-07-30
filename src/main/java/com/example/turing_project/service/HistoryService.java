package com.example.turing_project.service;

import com.example.turing_project.entity.Message;
import com.example.turing_project.repo.AnswerRepo;
import com.example.turing_project.repo.MessageRepo;
import com.example.turing_project.repo.QuestionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class HistoryService {
    private final MessageRepo messageRepo;
    private final AnswerRepo answerRepo;
    private final QuestionRepo questionRepo;

    public Message getMessageById(Long id) {
        Optional<Message> messageOptional = messageRepo.findById(id);
        return messageOptional.orElse(null);
    }

    public List<Message> getAllUserMessages(Long userId) {
        return messageRepo.getAllByEmployee_Id(userId);
    }

    public void saveMessage(Message message) {
        answerRepo.save(message.getAnswer());
        questionRepo.save(message.getQuestion());
        messageRepo.save(message);
    }
}
