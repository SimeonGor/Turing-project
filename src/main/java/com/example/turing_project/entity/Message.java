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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_id_seq")
    @SequenceGenerator(name = "message_id_seq", sequenceName = "message_seq", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "dialog_id", nullable = false)
    private Dialog dialog;

    @OneToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @OneToOne
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer answer;

    @Column(nullable = false)
    private LocalDateTime created;
}
