package com.example.turing_project.repo;

import com.example.turing_project.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MessageRepo extends JpaRepository<Message, Long> {
    Optional<Message> findById(Long id);

    @Query(value = """
        SELECT * FROM
            (SELECT * FROM message
            WHERE dialog_id = ?1
            ORDER BY created DESC
            LIMIT ?2) as "m*"
        ORDER BY created
    """, nativeQuery = true)
    List<Message> getContextHistory(Long dialogId, Long limits);
}
