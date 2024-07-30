package com.example.turing_project.service;

import com.example.turing_project.entity.Message;
import com.example.turing_project.repo.MessageRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {
    final MessageRepo messageRepo;

    public HistoryService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public Message getMessageById(Long id) {
        Optional<Message> messageOptional = messageRepo.findById(id);
        return messageOptional.orElse(null);
    }

    public List<Message> getAllUserMessages(Long userId) {
        return messageRepo.getAllByEmployee_Id(userId);
    }

    public void saveMessage(Message message) {
        messageRepo.save(message);
    }
}
