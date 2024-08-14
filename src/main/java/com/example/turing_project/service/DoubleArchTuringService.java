package com.example.turing_project.service;

import com.example.turing_project.dto.AnswerDto;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@Slf4j
public class DoubleArchTuringService implements TuringService {
    private final DocumentService documentService;

    public boolean hasDocumentName(String text) {
        String regex = "(?://)(.+?)(?://)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String possibleDocName = text.substring(matcher.start() + 2, matcher.end() - 2);
            log.trace("Нашли возможное название документа {}", possibleDocName);
            if (documentService.checkExistence(possibleDocName)) {
                return true;
            }
        }

        return false;
    }

    @SneakyThrows
    @Override
    public AnswerDto handle(String request) {
        if (hasDocumentName(request)) {
            log.info("Запрос {} перенаправлен в Lama3", request);
            Thread.sleep(500);
            // TODO: 14.08.2024 добавить логику

            return AnswerDto.builder()
                    .text("Answer to %s has been handled by Lama3".formatted(request))
                    .build();
        }
        else {
            log.info("Запрос {} перенаправлен в GigaChat", request);
            Thread.sleep(500);
            // TODO: 14.08.2024 добавить логику
            return AnswerDto.builder()
                    .text("Answer to %s has been handled by GigaChat".formatted(request))
                    .build();
        }
    }
}
