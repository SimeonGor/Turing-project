package com.example.turing_project.llama3.exceptions;

public class InvalidLlamaRequestException extends RuntimeException {
    public InvalidLlamaRequestException(String message) {
        super(message);
    }
}
