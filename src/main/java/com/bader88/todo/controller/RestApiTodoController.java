package com.bader88.todo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bader88.todo.model.entity.TodoEntity;
import com.bader88.todo.model.repo.ToDoRepo;
import com.bader88.todo.service.ToDoService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class RestApiTodoController {
	
	private final ToDoService toDoService;
	private final ToDoRepo toDoRepo;
	
	@GetMapping("/users/{username}/todos")
	public List<TodoEntity> getTodosByUsername(@PathVariable String username) {
//		return toDoService.findByUsername(username);
		return toDoRepo.findByUsername(username);
	}
	
}
