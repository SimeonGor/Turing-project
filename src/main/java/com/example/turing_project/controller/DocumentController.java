package com.example.turing_project.controller;

import com.example.turing_project.service.DocumentService;
import com.example.turing_project.service.StorageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("/files")
@Tag(name = "Documents", description = "documents and meetings controller")
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("upload")
    public void fileUpload(@RequestParam(name = "project") String projectName, @RequestParam(name = "file") MultipartFile file) {
        documentService.upload(file.getOriginalFilename(), projectName, file);
    }
}
