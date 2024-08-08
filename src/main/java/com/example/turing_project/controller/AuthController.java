package com.example.turing_project.controller;

import com.example.turing_project.entity.Employee;
import com.example.turing_project.service.EmployeeService;
import com.example.turing_project.service.JwtTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final EmployeeService employeeService;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Employee employee) {
        if (employeeService.getEmployeeByEmail(employee.getEmail()) != null) {
            return ResponseEntity.status(409).body("Conflict");
        }
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeService.save(employee);
        try {
            String token = jwtTokenService.generateToken(employee.getEmail(), employee.getPassword());
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            return ResponseEntity.ok().headers(headers).body("Registration successful\n" +
                    "Bearer " + token);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        try {
            String email = loginData.get("email");
            String password = loginData.get("password");
            Employee employee = employeeService.getEmployeeByEmail(email);

            if (employee != null && passwordEncoder.matches(password, employee.getPassword())) {
                String token = jwtTokenService.generateToken(email, password);
                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", "Bearer " + token);
                return ResponseEntity.ok().headers(headers).body("Login successful\n" +
                        "Bearer " + token);
            } else {
                return ResponseEntity.status(401).body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }


}
