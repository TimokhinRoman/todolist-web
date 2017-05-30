package ru.timokhin.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.timokhin.todolist.dao.TodoRepository;
import ru.timokhin.todolist.dao.UserRepository;
import ru.timokhin.todolist.model.Todo;
import ru.timokhin.todolist.model.User;
import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public Collection<Todo> getAllTodo(Principal principal) {
        String username = principal.getName();
        return todoRepository.findByUserName(username);
    }

    @PostMapping("/save")
    public Todo saveTodo(Principal principal, @RequestBody Todo todo) {
        User user = userRepository.findUserByName(principal.getName());
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    @PostMapping("/remove")
    public void removeTodo(Principal principal, @RequestParam Long id) {
        User user = userRepository.findUserByName(principal.getName());
        Todo todo = todoRepository.findOne(id);
        if (todo != null && todo.getUser().getId().equals(user.getId())) {
            todoRepository.delete(id);
        }
    }
}
