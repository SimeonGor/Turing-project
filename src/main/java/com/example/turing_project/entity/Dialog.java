package com.example.turing_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Dialog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @Column(nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private String title;

    @OneToMany
    @JoinColumn(name = "dialog_id")
    private List<Message> messageList;
}
