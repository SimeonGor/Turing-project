package com.example.turing_project.service;

import com.example.turing_project.dto.AnswerDto;
import com.example.turing_project.dto.DialogDto;
import com.example.turing_project.dto.MessageDto;
import com.example.turing_project.dto.QuestionDto;
import com.example.turing_project.entity.*;
import com.example.turing_project.exceptions.ResourceNotFoundException;
import com.example.turing_project.repo.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class HistoryService {
    private final MessageRepo messageRepo;
    private final AnswerRepo answerRepo;
    private final QuestionRepo questionRepo;
    private final DialogRepo dialogRepo;

    public MessageDto getMessageById(Employee employee, Long id) {
        Optional<Message> messageOptional = messageRepo.findById(id);
        return messageOptional
                .filter(message -> message.getDialog().getEmployee().getId().equals(employee.getId()))
                .map(MessageDto::of)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Message with id %s not found".formatted(id))
                );
    }

    public List<MessageDto> getDialogById(Employee employee, Long id) {
        Optional<Dialog> optionalDialog = dialogRepo.findById(id);
        return optionalDialog
                .filter(dialog -> dialog.getEmployee().getId().equals(employee.getId()))
                .map(dialog ->
                    dialog.getMessageList().stream().map(MessageDto::of).toList())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Dialog with id %s not found".formatted(id))
                );
    }

    public List<DialogDto> getAllDialogs(Employee employee) {
        return dialogRepo.findAllByEmployee_Id(employee.getId())
                .stream().map(DialogDto::of).toList();
    }

    public DialogDto createDialog(Employee employee, String title) {
        LocalDateTime time = LocalDateTime.now();

        Dialog dialog = new Dialog();
        dialog.setEmployee(employee);
        dialog.setTitle(title);
        dialog.setCreated(time);
        dialog.setModified(time);

        Dialog saved = dialogRepo.save(dialog);
        return DialogDto.of(saved);
    }

    @Transactional
    public MessageDto saveMessage(Employee employee, Long dialogId, QuestionDto questionDto, AnswerDto answerDto) {
        Answer answer = answerDto.toAnswer();
        Question question = questionDto.toQuestion();

        Optional<Dialog> optionalDialog = dialogRepo.findById(dialogId);
        if (optionalDialog.filter(dialog -> dialog.getEmployee().getId().equals(employee.getId())).isEmpty()) {
            throw new ResourceNotFoundException("Dialog with id %s not found".formatted(dialogId));
        }
        LocalDateTime time = LocalDateTime.now();

        Dialog dialog = optionalDialog.get();
        dialog.setModified(time);
        if (dialog.getMessageList().isEmpty()) {
            dialog.setTitle(question.getText());
        }

        Message message = new Message();
        message.setAnswer(answer);
        message.setQuestion(question);
        message.setDialog(dialog);
        message.setCreated(time);

        dialogRepo.save(dialog);
        answerRepo.save(answer);
        questionRepo.save(question);
        Message saved = messageRepo.save(message);

        return MessageDto.of(saved);
    }
}
