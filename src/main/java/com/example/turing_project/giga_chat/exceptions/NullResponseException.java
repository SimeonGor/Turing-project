package com.example.turing_project.giga_chat.exceptions;

public class NullResponseException extends RuntimeException{
    public NullResponseException(String message) {
        super(message);
    }

    public NullResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
