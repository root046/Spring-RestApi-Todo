package com.bader88.todo.controller;

import com.bader88.todo.authentication.credintail;
import com.bader88.todo.model.entity.TodoEntity;
import com.bader88.todo.service.ToDoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//@Controller
@RequiredArgsConstructor
@SessionAttributes("name")
public class MainController {

    private final ToDoService toDoService;


    @RequestMapping("get-todo-list")
    public String getToDOList(ModelMap modelMap){
        String username = getLoggedInUsername(modelMap);
        List<TodoEntity> ToDos = toDoService.findByUsername(username);
        modelMap.addAttribute("ToDos",ToDos);
        return "list-todo";// return to login.jsp
    }

    @RequestMapping(value = "add-todo",method = RequestMethod.GET)
    public String showNewToDoPage(ModelMap modelMap){
        String username = getLoggedInUsername(modelMap);
        TodoEntity todoEntity = new TodoEntity(0, username, "", LocalDate.now().plusWeeks(1), false);
        modelMap.put("todo",todoEntity);
        return "todo";
    }

    @RequestMapping(value = "add-todo",method = RequestMethod.POST)
    public String saveTodo(ModelMap modelMap, @Valid @ModelAttribute("todo") TodoEntity todoEntity, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "todo";
        }
        String username = getLoggedInUsername(modelMap);
        toDoService.addToDo(username,todoEntity.getDescription(), todoEntity.getTargetDate(),false);
        return "redirect:get-todo-list"; // redirect to another method in controller by endpoint name
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
        //Delete todo

        toDoService.deleteById(id);
        return "redirect:get-todo-list";
    }

    @RequestMapping(value="update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        TodoEntity todo = toDoService.findById(id);
        model.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value="update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid TodoEntity todo, BindingResult result) {

        if(result.hasErrors()) {
            return "todo";
        }

        String username = getLoggedInUsername(model);
        todo.setUsername(username);
        toDoService.updateTodo(todo);
        return "redirect:get-todo-list";
    }

    private static String getLoggedInUsername(ModelMap modelMap) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
