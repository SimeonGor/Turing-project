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

    private MessageDto messageDtoMapper(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .created(message.getCreated())
                .answer(AnswerDto.builder()
                        .text(message.getAnswer().getText())
                        .document(message.getAnswer().getDocument())
                        .build()
                )
                .question(QuestionDto.builder()
                        .text(message.getQuestion().getText())
                        .build()
                )
                .build();
    }

    private DialogDto dialogDtoMapper(Dialog dialog) {
        return DialogDto.builder()
                .id(dialog.getId())
                .title(dialog.getTitle())
                .build();
    }

    private Answer answerMapper(AnswerDto answerDto) {
        Answer answer = new Answer();
        answer.setType("text");
        answer.setDocument(answerDto.getDocument());
        answer.setText(answerDto.getText());
        return answer;
    }

    private Question questionMapper(QuestionDto questionDto) {
        Question question = new Question();
        question.setText(questionDto.getText());
        return question;
    }

    public MessageDto getMessageById(Employee employee, Long id) {
        Optional<Message> messageOptional = messageRepo.findById(id);
        return messageOptional
                .filter(message -> message.getDialog().getEmployee().getId().equals(employee.getId()))
                .map(this::messageDtoMapper)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Message with id %s not found".formatted(id))
                );
    }

    public List<MessageDto> getDialogById(Employee employee, Long id) {
        Optional<Dialog> optionalDialog = dialogRepo.findById(id);
        return optionalDialog
                .filter(dialog -> dialog.getEmployee().getId().equals(employee.getId()))
                .map(dialog ->
                    dialog.getMessageList().stream().map(this::messageDtoMapper).toList())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Dialog with id %s not found".formatted(id))
                );
    }

    public List<DialogDto> getAllDialogs(Employee employee) {
        return dialogRepo.findAllByEmployee_Id(employee.getId())
                .stream().map(this::dialogDtoMapper).toList();
    }

    public DialogDto createDialog(Employee employee, String title) {
        Dialog dialog = new Dialog();
        dialog.setEmployee(employee);
        dialog.setTitle(title);

        Dialog saved = dialogRepo.save(dialog);
        return dialogDtoMapper(saved);
    }

    public MessageDto saveMessage(Employee employee, Long dialogId, QuestionDto questionDto, AnswerDto answerDto) {
        Answer answer = answerMapper(answerDto);
        Question question = questionMapper(questionDto);

        Optional<Dialog> optionalDialog = dialogRepo.findById(dialogId);
        if (optionalDialog.filter(dialog -> dialog.getEmployee().getId().equals(employee.getId())).isEmpty()) {
            throw new ResourceNotFoundException("Dialog with id %s not found".formatted(dialogId));
        }

        Message message = new Message();
        message.setAnswer(answer);
        message.setQuestion(question);
        message.setDialog(optionalDialog.get());
        message.setCreated(LocalDateTime.now());

        answerRepo.save(answer);
        questionRepo.save(question);
        Message saved = messageRepo.save(message);

        return messageDtoMapper(saved);
    }
}
