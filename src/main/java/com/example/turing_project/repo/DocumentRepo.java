package com.example.turing_project.repo;

import com.example.turing_project.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepo extends JpaRepository<Document, Long> {
    Optional<Document> findByName(String name);
}
