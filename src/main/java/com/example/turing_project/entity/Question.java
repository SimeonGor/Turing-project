package com.example.turing_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_id_seq")
    @SequenceGenerator(name = "question_id_seq", sequenceName = "question_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String text;
}
