package com.bader88.todo.model.repo;

import com.bader88.todo.model.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepo extends JpaRepository<TodoEntity,Integer> {
    public List<TodoEntity> findByUsername(String username);
}
