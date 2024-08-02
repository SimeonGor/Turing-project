package com.example.turing_project.repo;

import com.example.turing_project.entity.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DialogRepo extends JpaRepository<Dialog, Long> {
    Optional<Dialog> findById(Long id);

    List<Dialog> findAllByEmployee_Id(Long id);
}
