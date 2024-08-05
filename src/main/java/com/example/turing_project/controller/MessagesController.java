package com.example.turing_project.controller;

import com.example.turing_project.dto.AnswerDto;
import com.example.turing_project.dto.DialogDto;
import com.example.turing_project.dto.MessageDto;
import com.example.turing_project.dto.QuestionDto;
import com.example.turing_project.entity.Employee;
import com.example.turing_project.service.EmployeeService;
import com.example.turing_project.service.HistoryService;
import com.example.turing_project.service.TuringService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

// TODO: 01.08.2024 необходима проверка принадлежности к пользователю
@AllArgsConstructor
@RestController
@RequestMapping("/turing")
public class MessagesController {
    private final HistoryService historyService;
    private final TuringService turingService;
    @Autowired
    private EmployeeService employeeService;


    @GetMapping("messages/{messageId}")
    public MessageDto getMessage(@PathVariable Long messageId) {
        MessageDto message = historyService.getMessageById(messageId);
        if (message == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such message id");
        }
        return message;
    }

    @GetMapping("dialogs")
    public List<DialogDto> getAllUserDialogs() {
        // FIXME: 01.08.2024 необходим пользователь
        return historyService.getAllDialogs(1L);
    }

    @GetMapping("dialogs/{dialogId}/messages")
    public List<MessageDto> getDialog(@PathVariable Long dialogId) {
        List<MessageDto> messageDtoList = historyService.getDialogById(dialogId);
        if (messageDtoList == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such dialog id");
        }
        return messageDtoList;
    }

    @PostMapping("dialogs/new")
    public DialogDto startNewDialog() {
        return historyService.createDialog(1L, "New dialog %s".formatted(LocalDateTime.now().toString()));
    }

    @PostMapping("dialogs/{dialogId}/send")
    public AnswerDto sendQuestion(@PathVariable Long dialogId, @RequestBody @Valid QuestionDto question) {
        System.out.println(question.getText());
        AnswerDto answer = turingService.handle(question.getText());
        historyService.saveMessage(dialogId, question, answer);

        return answer;
    }

    private Employee getCurrentEmployee() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            throw new RuntimeException("User not authenticated");

        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();

        return employeeService.getEmployeeByEmail(email);
    }

}
