package com.example.turing_project.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    // TODO: 30.07.2024 необходимо подумать какие значения может принимать это поле
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String text;
    private String document;
}
