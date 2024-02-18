package com.bader88.todo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bader88.todo.model.entity.TodoEntity;
import com.bader88.todo.model.repo.ToDoRepo;
import com.bader88.todo.service.ToDoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestApiTodoController {

//	private final ToDoService toDoService;
	private final ToDoRepo toDoRepo;
	
	@GetMapping("/")
	public String Welcome() {
		return "every things fine ^_^";
	}
	@GetMapping("/basicauth")
	public String basicAuthCheck() {
		return "Success";
	}

	@GetMapping("/users/{username}/todos")
	public List<TodoEntity> getTodosByUsername(@PathVariable String username) {
//		return toDoService.findByUsername(username);
		return toDoRepo.findByUsername(username);
	}

	@GetMapping("/users/{username}/todos/{id}")
	public TodoEntity getTodoByUsernameAndId(@PathVariable String username, @PathVariable int id) {
//		return toDoService.findById(id);

		return toDoRepo.findById(id).get();
//		return toDoRepo.findByUsernameAndId(username, id);
	}

	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodoByUsernameAndId(@PathVariable String username, @PathVariable int id) {
//		toDoService.deleteById(id);

		toDoRepo.deleteById(id);
//		toDoRepo.removeByUsernameAndId(username, id);

		return ResponseEntity.noContent().build();
	}

	@PutMapping("/users/{username}/todos/{id}")
	public TodoEntity updateTodoByUsernameAndId(@PathVariable String username, @PathVariable int id,
			@RequestBody TodoEntity todoEntity) {

//		toDoService.updateTodo(todoEntity);
		toDoRepo.save(todoEntity);
		return todoEntity;
	}
	
	@PostMapping("/users/{username}/todos")
	public TodoEntity createTodoByUsernameAndId(@PathVariable String username,@RequestBody TodoEntity todoEntity) {
		
		todoEntity.setUsername(username);
		todoEntity.setId(null);
		
		return toDoRepo.save(todoEntity);

//		TodoEntity createdToDo = toDoService.addToDo(username, todoEntity.getDescription(), todoEntity.getTargetDate(), todoEntity.getIsDone());
//		
//		return createdToDo;
	}

}
