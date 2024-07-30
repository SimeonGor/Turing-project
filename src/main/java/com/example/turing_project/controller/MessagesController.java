package com.example.turing_project.controller;

import com.example.turing_project.entity.Answer;
import com.example.turing_project.entity.Message;
import com.example.turing_project.entity.Question;
import com.example.turing_project.service.EmployeeService;
import com.example.turing_project.service.HistoryService;
import com.example.turing_project.service.TuringService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/turing")
public class MessagesController {
    private final HistoryService historyService;
    private final TuringService turingService;
    private final EmployeeService employeeService;

    @GetMapping("users/{userId}/messages/{messageId}")
    public Message getMessage(@PathVariable Long userId, @PathVariable Long messageId) {
        Message message = historyService.getMessageById(messageId);
        if (message == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such message id");
        }
        return message;
    }

    @GetMapping("users/{userId}/messages")
    public List<Message> getAllMessages(@PathVariable Long userId) {
        return historyService.getAllUserMessages(userId);
    }

    // TODO: 30.07.2024 переделать определение пользователя
    @PostMapping("users/{userId}/send")
    public Answer sendQuestion(@PathVariable Long userId, @RequestBody Question question) {
        Answer answer = turingService.handle(question.getText());
        Message message = new Message();
        message.setQuestion(question);
        message.setAnswer(answer);
        message.setEmployee(employeeService.getEmployeeById(userId));

        historyService.saveMessage(message);

        return answer;
    }
}
