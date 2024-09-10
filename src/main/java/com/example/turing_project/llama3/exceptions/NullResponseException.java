package com.example.turing_project.llama3.exceptions;

public class NullResponseException extends RuntimeException{
    public NullResponseException(String message) {
        super(message);
    }
}
