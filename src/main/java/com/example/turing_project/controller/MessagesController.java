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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/turing")
public class MessagesController {
    private final HistoryService historyService;
    private final TuringService turingService;
    private final EmployeeService employeeService;


    @GetMapping("messages/{messageId}")
    public MessageDto getMessage(@PathVariable Long messageId) {
        Employee employee = employeeService.getCurrentEmployee();
        MessageDto message = historyService.getMessageById(employee, messageId);
        if (message == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such message id");
        }
        return message;
    }

    @GetMapping("dialogs")
    public List<DialogDto> getAllUserDialogs() {
        Employee employee = employeeService.getCurrentEmployee();
        return historyService.getAllDialogs(employee);
    }

    @GetMapping("dialogs/{dialogId}/messages")
    public List<MessageDto> getDialog(@PathVariable Long dialogId) {
        Employee employee = employeeService.getCurrentEmployee();
        List<MessageDto> messageDtoList = historyService.getDialogById(employee, dialogId);
        if (messageDtoList == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such dialog id");
        }
        return messageDtoList;
    }

    @PostMapping("dialogs/new")
    public DialogDto createNewDialog() {
        Employee employee = employeeService.getCurrentEmployee();
        return historyService.createDialog(employee, "New dialog %s".formatted(LocalDateTime.now().toString()));
    }

    @PostMapping("dialogs/{dialogId}/send")
    public MessageDto sendQuestion(@PathVariable Long dialogId, @RequestBody @Valid QuestionDto question) {
        Employee employee = employeeService.getCurrentEmployee();
        AnswerDto answer = turingService.handle(question.getText());

        return historyService.saveMessage(employee, dialogId, question, answer);
    }


}
