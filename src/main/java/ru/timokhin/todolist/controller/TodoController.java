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
public class TodoController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoRepository todoRepository;

    @RequestMapping(value = "/todo", method = RequestMethod.GET)
    public Collection<Todo> getAllTodos(Principal principal) {
        String username = principal.getName();
        return todoRepository.findByUserName(username);
    }

    @RequestMapping(value = "/todo/save", method = RequestMethod.POST)
    public Todo saveTodo(Principal principal, @RequestBody Todo todo) {
        User user = userRepository.findUserByName(principal.getName());
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    @RequestMapping(value = "/todo/remove", method = RequestMethod.GET)
    public void removeTodo(Principal principal, @RequestParam Long id) {
        User user = userRepository.findUserByName(principal.getName());
        Todo todo = todoRepository.findOne(id);
        if (todo != null & todo.getUser().equals(user)) {
            todoRepository.delete(id);
        }
    }
}
