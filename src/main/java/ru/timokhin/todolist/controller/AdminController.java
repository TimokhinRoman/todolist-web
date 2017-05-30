package ru.timokhin.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.timokhin.todolist.UserNotFoundException;
import ru.timokhin.todolist.dao.TodoRepository;
import ru.timokhin.todolist.dao.UserRepository;
import ru.timokhin.todolist.model.Todo;
import ru.timokhin.todolist.model.User;
import java.util.Collection;

@RestController
@RequestMapping("/users")
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{username}/todo")
    public Collection<Todo> getAllTodoForUser(@PathVariable String username) {
        User user = userRepository.findUserByName(username);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        return todoRepository.findByUserName(username);
    }

    @PostMapping("/{username}/todo/save")
    public Todo saveTodo(@PathVariable String username, @RequestBody Todo todo) {
        User user = userRepository.findUserByName(username);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    @PostMapping("/{username}/todo/remove")
    public void removeTodo(@PathVariable String username, @RequestParam Long id) {
        User user = userRepository.findUserByName(username);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        Todo todo = todoRepository.findOne(id);
        if (todo != null && todo.getUser().getId().equals(user.getId())) {
            todoRepository.delete(id);
        }
    }
}
