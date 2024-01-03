package com.example.todospringboot.controller;

import com.example.todospringboot.model.Todo;
import com.example.todospringboot.repository.TodoJpaRepository;
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
public class TodoJPAResource {

    @Autowired
    private TodoJpaRepository jpaRepository;

    @Autowired
    private TodoHardcodedService service;

    @GetMapping("/jpa/users/{username}/todos")
    public List<Todo> getTodoList(@PathVariable String username) {
        return jpaRepository.findByUsername(username);
    }

    @GetMapping("/jpa/users/{username}/todos/{id}")
    public Todo getTodo(@PathVariable String username, @PathVariable long id) {
        return jpaRepository.findById(id).get();
    }

    @DeleteMapping("/jpa/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
        Todo todo = jpaRepository.findById(id).get();
        if(todo == null) {
            return ResponseEntity.noContent().build();
        } else {
            jpaRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/jpa/users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateToDo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo) {
        Todo td = jpaRepository.save(todo);
        return new ResponseEntity<Todo>(td, HttpStatus.ACCEPTED);
    }

    @PostMapping("/jpa/users/{username}/todo")
    public ResponseEntity<Todo> saveToDo(@PathVariable String username, @RequestBody Todo todo) {
        todo.setUsername(username);
        Todo createdTodo = jpaRepository.save(todo);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
