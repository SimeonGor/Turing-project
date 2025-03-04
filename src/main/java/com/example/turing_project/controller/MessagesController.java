package com.example.turing_project.controller;

import com.example.turing_project.dto.*;
import com.example.turing_project.entity.Employee;
import com.example.turing_project.exceptions.ResourceNotFoundException;
import com.example.turing_project.service.EmployeeService;
import com.example.turing_project.service.HistoryService;
import com.example.turing_project.service.TuringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/turing")
@Tag(name = "Messaging", description = "message sending controller")
public class MessagesController {
    private final HistoryService historyService;
    private final TuringService turingService;
    private final EmployeeService employeeService;

    @GetMapping("messages/{messageId}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Get message by id")
    public MessageDto getMessage(@PathVariable Long messageId) {
        Employee employee = employeeService.getCurrentEmployee();
        return historyService.getMessageById(employee, messageId);
    }

    @GetMapping("dialogs")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Get all user dialogs")
    public List<DialogDto> getAllUserDialogs() {
        Employee employee = employeeService.getCurrentEmployee();
        return historyService.getAllDialogs(employee);
    }

    @GetMapping("dialogs/{dialogId}/messages")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Get dialog by id")
    public List<MessageDto> getDialog(@PathVariable Long dialogId) {
        Employee employee = employeeService.getCurrentEmployee();
        return historyService.getDialogById(employee, dialogId);
    }

    @PostMapping("dialogs/new")
    @SecurityRequirement(name = "JWT")
    @Operation(summary ="Create new dialog")
    public DialogDto createNewDialog() {
        Employee employee = employeeService.getCurrentEmployee();
        return historyService.createDialog(employee, "New dialog %s".formatted(LocalDateTime.now().toString()));
    }

    @PostMapping("dialogs/{dialogId}/send")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Send new question")
    public MessageDto sendQuestion(@PathVariable Long dialogId, @RequestBody @Valid QuestionDto question) {
        Employee employee = employeeService.getCurrentEmployee();
        Long historyContextLimits = turingService.getHistoryContextLimits();
        HistoryContext historyContext = historyService.getHistoryContext(employee, dialogId, historyContextLimits);
        historyContext.getMessages().forEach(System.out::println);
        AnswerDto answer = turingService.handle(question.getText(), historyContext);
        return historyService.saveMessage(employee, dialogId, question, answer);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> catchResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity.
                status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().statusCode(HttpStatus.NOT_FOUND.value()).message(e.getMessage()).build());
    }
}
