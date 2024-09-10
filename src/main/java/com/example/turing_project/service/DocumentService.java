package com.example.turing_project.service;

import com.example.turing_project.entity.Document;
import com.example.turing_project.repo.DocumentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class DocumentService {
    private final DocumentRepo documentRepo;
    private final StorageService storageService;

    public boolean checkExistence(String documentName) {
        return documentRepo.findByName(documentName).isPresent();
    }

    @Transactional
    public void upload(String name, String project, MultipartFile file) {
        Path stored = storageService.store(file);
        Document document = Document.builder()
                .name(name)
                .project(project)
                .path(stored.toString())
                .created(LocalDateTime.now())
                .build();
        
        documentRepo.save(document);
    }

    // TODO: 14.08.2024 рассмотреть другие методы 
}
