package com.example.turing_project.service;

import com.example.turing_project.entity.Employee;
import com.example.turing_project.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepo.findById(id);
        return employeeOptional.orElse(null);
    }

    public Employee getEmployeeByEmail(String email) {
        Optional<Employee> employeeOptional = employeeRepo.findByEmail(email);
        return employeeOptional.orElse(null);
    }

    public Employee getEmployeeByUsername(String username) {
        Optional<Employee> employeeOptional = employeeRepo.findByUsername(username);
        return employeeOptional.orElse(null);
    }

    public Employee save(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee register(Employee employee) {
        if (employeeRepo.findByEmail(employee.getEmail()).isPresent()) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }
        return save(employee);
    }

    public Employee getCurrentEmployee() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails userDetails)) {
            throw new RuntimeException("User not authenticated");

        }
        String email = userDetails.getUsername();
        return getEmployeeByEmail(email);
    }


}
