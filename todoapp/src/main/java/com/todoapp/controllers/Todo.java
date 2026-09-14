package com.todoapp.controllers;

import com.todoapp.service.todo.TodoService;
import com.todoapp.dto.TodoDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class Todo {
    private final TodoService todoService;

    @Autowired
    public Todo(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("")
    public httpResponse createTodo(@RequestBody TodoDTO.createTodo todo) {
        try{
            this.todoService.createTodo(todo);
            return new httpResponse("todo created", null);
        }catch(Exception e){
            return new httpResponse(e.getMessage(),null);
        }
    }

    @GetMapping("")
    public httpResponse getTodos(TodoDTO.searchParams params) {
        try{
            List<TodoDTO.todo> todos = this.todoService.getTodos(params);
            return new httpResponse("todos fetched", todos);
        }catch(Exception e){    
            return new httpResponse(e.getMessage(), null);
        }
    }

    @GetMapping("/{id}")
    public httpResponse getTodo(@PathVariable String id) {
        try{
           TodoDTO.todo todo = this.todoService.getTodo(id);
            return new httpResponse("request successful", todo);
        }catch(Exception e){
            return new httpResponse(e.getMessage(), null);
        }
    }

    @PutMapping("/{id}")
    public httpResponse updateTodo(@PathVariable String id, @RequestBody TodoDTO.update todo) {
        try{
            this.todoService.updateTodo(id, todo);
            return new httpResponse("updated todo", null);
        }catch(Exception e){
            return new httpResponse(e.getMessage(), null);
        }
    }

    @PutMapping("/{id}/complete")
    public httpResponse markTodoComplete(@PathVariable String id) {
        try{
            this.todoService.markAsComplete(id);    
            return new httpResponse("todo marked complete", id);
        }catch(Exception e){
            return new httpResponse(e.getMessage(), null);
        }
    }

    @DeleteMapping("/{id}")
    public httpResponse deleteTodo(@PathVariable String id) {
         try{   
            this.todoService.deleteTodo(id);
            return new httpResponse("todo deleted successfully", id);
        }catch(Exception e){
            return new httpResponse(e.getMessage(), null);
        }
    }
}

record httpResponse(
    String message,
    Object data
){}