package com.example.turing_project.controller;

import com.example.turing_project.service.MyEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.turing_project.entity.MyEntity;
import java.util.List;

@RestController
@RequestMapping("/api/entities")
public class MyEntityController {
    @Autowired
    private MyEntityService service;

    @GetMapping
    public List<MyEntity> getAllEntities() {
        return service.getAllEntities();
    }

    @GetMapping("/{id}")
    public MyEntity getEntityById(@PathVariable Long id) {
        return service.getEntityById(id);
    }

    @PostMapping
    public MyEntity createEntity(@RequestBody MyEntity entity) {
        return service.saveEntity(entity);
    }

    @PutMapping("/{id}")
    public MyEntity updateEntity(@PathVariable Long id, @RequestBody MyEntity entity) {
        entity.setId(id);
        return service.saveEntity(entity);
    }

    @DeleteMapping("/{id}")
    public void deleteEntity(@PathVariable Long id) {
        service.deleteEntity(id);
    }
}
