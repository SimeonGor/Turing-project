package com.example.turing_project.repo;

import com.example.turing_project.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public  interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByUsername(String username);

}
