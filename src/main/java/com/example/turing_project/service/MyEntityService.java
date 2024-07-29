package com.example.turing_project.service;

import com.example.turing_project.entity.MyEntity;
import com.example.turing_project.repo.MyEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MyEntityService {
    @Autowired
    private MyEntityRepository repository;

    public List<MyEntity> getAllEntities() {
        return repository.findAll();
    }

    public MyEntity getEntityById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public MyEntity saveEntity(MyEntity entity) {
        return repository.save(entity);
    }

    public void deleteEntity(Long id) {
        repository.deleteById(id);
    }
}
