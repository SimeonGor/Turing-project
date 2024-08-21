package com.example.turing_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_id_seq")
    @SequenceGenerator(name = "document_id_seq", sequenceName = "document_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String project;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false, length = 1000)
    private String path;

    @Column(nullable = false)
    private LocalDateTime created;
}
