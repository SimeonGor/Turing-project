package com.example.turing_project.service;

import com.example.turing_project.entity.Employee;
import com.example.turing_project.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepo.findById(id);
        return employeeOptional.orElse(new Employee());
    }
}
