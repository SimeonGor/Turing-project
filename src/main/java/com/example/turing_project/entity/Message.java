package com.example.turing_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "dialog_id")
    private Dialog dialog;

    @OneToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    private LocalDateTime created;
}
