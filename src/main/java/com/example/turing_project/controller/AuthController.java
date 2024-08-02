package com.example.turing_project.controller;

import com.example.turing_project.entity.Employee;
import com.example.turing_project.service.EmployeeService;
import com.example.turing_project.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Employee employee) {
        if (employeeService.getEmployeeByEmail(employee.getEmail())!=null){
            return ResponseEntity.status(409).body("Conflict");
        }
        // Хеширование пароля перед сохранением
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeService.save(employee);
        return ResponseEntity.ok("Registration successful");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        try {
            String username = loginData.get("username");
            String password = loginData.get("password");
            Employee employee = employeeService.getEmployeeByUsername(username);

            // Проверка пароля
            if (employee != null && passwordEncoder.matches(password, employee.getPassword())) {
                // Генерация токена
                String token = jwtTokenService.generateToken(username, password);
                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(401).body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}
