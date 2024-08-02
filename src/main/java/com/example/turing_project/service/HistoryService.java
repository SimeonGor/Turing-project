package com.example.turing_project.service;

import com.example.turing_project.dto.AnswerDto;
import com.example.turing_project.dto.DialogDto;
import com.example.turing_project.dto.MessageDto;
import com.example.turing_project.dto.QuestionDto;
import com.example.turing_project.entity.Answer;
import com.example.turing_project.entity.Dialog;
import com.example.turing_project.entity.Message;
import com.example.turing_project.entity.Question;
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
    private final EmployeeRepo employeeRepo;

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

    public MessageDto getMessageById(Long id) {
        Optional<Message> messageOptional = messageRepo.findById(id);
        return messageOptional
                .map(this::messageDtoMapper
                )
                .orElse(null);
    }

    public List<MessageDto> getDialogById(Long id) {
        Optional<Dialog> optionalDialog = dialogRepo.findById(id);
        return optionalDialog
                .map(dialog ->
                    dialog.getMessageList().stream().map(this::messageDtoMapper).toList()
                ).orElse(null);
    }

    public List<DialogDto> getAllDialogs(Long userId) {
        return dialogRepo.findAllByEmployee_Id(userId)
                .stream().map(this::dialogDtoMapper).toList();
    }

    // TODO: 01.08.2024 change userId to employee
    public DialogDto createDialog(Long userId, String title) {
        Dialog dialog = new Dialog();
        dialog.setEmployee(employeeRepo.findById(userId).get());
        dialog.setTitle(title);

        Dialog saved = dialogRepo.save(dialog);
        return dialogDtoMapper(saved);
    }

    public void saveMessage(Long dialogId, QuestionDto questionDto, AnswerDto answerDto) {
        Answer answer = answerMapper(answerDto);
        Question question = questionMapper(questionDto);

        Message message = new Message();
        message.setAnswer(answer);
        message.setQuestion(question);
        message.setDialog(dialogRepo.findById(dialogId).get());
        message.setCreated(LocalDateTime.now());

        answerRepo.save(answer);
        questionRepo.save(question);
        messageRepo.save(message);
    }
}
