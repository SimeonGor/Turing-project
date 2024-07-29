package com.example.turing_project.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class EmployeeController {
    @GetMapping("/signin")
    public ResponseEntity<String> signIn() {
        String message = "Welcome! You have successfully signed in.";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CustomHeaderValue");
        headers.add("Another-Header", "AnotherHeaderValue");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(message);
    }
}
