package com.example.todospringboot.service;

import com.example.todospringboot.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoHardcodedService {

    private static List<Todo> todos = new ArrayList<>();

    private static int idCounter = 0;

    static {
        todos.add(new Todo(++idCounter, "Todo Application", "Go for a walk", new Date(), false));
        todos.add(new Todo(++idCounter, "Todo Application", "Learn micro services", new Date(), false));
        todos.add(new Todo(++idCounter, "Todo Application", "Learn about micro services", new Date(), false));
        todos.add(new Todo(++idCounter, "Todo Application", "Learn about angular", new Date(), false));
        todos.add(new Todo(++idCounter, "Todo Application", "Learn about angular 11", new Date(), true));
        todos.add(new Todo(++idCounter, "Todo Application", "Learn about java security ", new Date(), true));
    }

    public Todo deleteById(long id) {
        Todo todo = findById(id);
        todos.remove(todo);
        return todo;
    }

    public Todo save(Todo todo) {
        if(todo.getId() == -1 || todo.getId() == 0) {
            todo.setId(++idCounter);
            todos.add(todo);
        } else {
            deleteById(todo.getId());
            todos.add(todo);
        }
        return todo;
    }

    public Todo findById(long id) {
        for (Todo todo : todos) {
            if (todo == null) return null;
            if (todo.getId() == id) {
                return todo;
            }
        }
        return null;
    }

    public List<Todo> findAll() {
        return todos;
    }
}
