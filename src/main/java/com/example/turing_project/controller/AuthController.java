package com.example.turing_project.controller;

import com.example.turing_project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AuthController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<String> register() {
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
