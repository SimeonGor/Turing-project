package com.example.turing_project.repo;

import com.example.turing_project.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
