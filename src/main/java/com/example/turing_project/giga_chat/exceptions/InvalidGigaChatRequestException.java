package com.example.turing_project.giga_chat.exceptions;

public class InvalidGigaChatRequestException extends RuntimeException {
    public InvalidGigaChatRequestException(String message) {
        super(message);
    }

    public InvalidGigaChatRequestException(String message, Throwable cause) {
        super(message,  cause);
    }
}
