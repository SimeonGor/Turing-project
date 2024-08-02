package com.example.turing_project.repo;

import com.example.turing_project.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepo extends JpaRepository<Message, Long> {
    Optional<Message> findById(Long id);
}
