package com.bader88.todo.service;

import com.bader88.todo.model.entity.TodoEntity;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ToDoService {
    private static List<TodoEntity> todoEntityList=new ArrayList<>();
    private static int idCounter=0;
    static {
        todoEntityList.add(new TodoEntity(++ idCounter,"bader","SpringBoot",
                LocalDate.now().plusYears(1),false));

        todoEntityList.add(new TodoEntity(++ idCounter,"bader","DotNet",
                LocalDate.now().plusYears(2),false));

        todoEntityList.add(new TodoEntity(++ idCounter,"bader","Outsystems",
                LocalDate.now().plusYears(3),false));
    }

    public List<TodoEntity> findByUsername(String username){
        Predicate<? super TodoEntity> predicate =
                todo -> todo.getUsername().equalsIgnoreCase(username);
        return todoEntityList.stream().filter(predicate).toList();
    }
    public void addToDo(String username,String description,LocalDate targetDate,boolean isDone){
        TodoEntity todoEntity = new TodoEntity(++ idCounter,username,description,targetDate,isDone);
        todoEntityList.add(todoEntity);
    }
    public void deleteById(int id) {
        //todo.getId() == id
        // todo -> todo.getId() == id
        Predicate<? super TodoEntity> predicate = todo -> todo.getId() == id;
        todoEntityList.removeIf(predicate);
    }

    public TodoEntity findById(int id) {
        Predicate<? super TodoEntity> predicate = todo -> todo.getId() == id;
        TodoEntity todoEntity = todoEntityList.stream().filter(predicate).findFirst().get();
        return todoEntity;
    }

    public void updateTodo(@Valid TodoEntity todo) {
        deleteById(todo.getId());
        todoEntityList.add(todo);
    }
}
