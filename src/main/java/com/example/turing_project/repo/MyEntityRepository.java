package com.example.turing_project.repo;

import com.example.turing_project.entity.MyEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MyEntityRepository extends JpaRepository<MyEntity, Long> {
}
