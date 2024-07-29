package com.example.turing_project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String type;
    private String text;
    //TODO: определить, в каком формате будет хранится документ
    private String document;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
