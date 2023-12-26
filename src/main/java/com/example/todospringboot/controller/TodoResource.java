package com.example.todospringboot.controller;

import com.example.todospringboot.model.Todo;
import com.example.todospringboot.service.TodoHardcodedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoResource {

    @Autowired
    private TodoHardcodedService service;

    @GetMapping("/users/{username}/todos")
    public List<Todo> getTodoList(@PathVariable String username) {
        return service.findAll();
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo getTodo(@PathVariable String username, @PathVariable long id) {
        return service.findById(id);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
        Todo todo = service.deleteById(id);
        if(todo == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateToDo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo) {
        Todo td = service.save(todo);
        return new ResponseEntity<Todo>(td, HttpStatus.ACCEPTED);
    }

    @PostMapping("/users/{username}/todo")
    public ResponseEntity<Todo> saveToDo(@PathVariable String username, @RequestBody Todo todo) {
        Todo createdTodo = service.save(todo);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
