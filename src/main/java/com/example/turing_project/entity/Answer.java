package com.example.turing_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_id_seq")
    @SequenceGenerator(name = "answer_id_seq", sequenceName = "answer_seq", allocationSize = 1)
    private Long id;
    // TODO: 30.07.2024 необходимо подумать какие значения может принимать это поле
    @Column(nullable = false)
    private String type;
    @Column(nullable = false, length = 4000)
    private String text;
    private String document;
}
