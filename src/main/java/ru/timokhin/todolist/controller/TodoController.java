package ru.timokhin.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.timokhin.todolist.bean.Todo;
import ru.timokhin.todolist.bean.User;
import ru.timokhin.todolist.dao.TodoRepository;
import ru.timokhin.todolist.dao.UserRepository;

import java.util.Collection;

@RestController
public class TodoController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoRepository todoRepository;

    @RequestMapping(value = "/{userName}/todo", method = RequestMethod.GET)
    public Collection<Todo> getAllTodos(@PathVariable String userName) {
        //User user = userRepository.findUserByName(userName).get();
        return todoRepository.findByUserName(userName);
    }

    @RequestMapping(value = "/{userName}/todo/save", method = RequestMethod.POST)
    public Todo saveTodo(@PathVariable String userName, @RequestBody Todo todo) {
        User user = userRepository.findUserByName(userName).get();

        todo.setUser(user);

        return todoRepository.save(todo);

        //return todoRepository.save(new Todo(todo.getText(), user));

        /*if (todo.getId() == null) {
            return todoRepository.save(new Todo(todo.getText(), user));
        } else {
            todoRepository.fin
        }*/
    }

    @RequestMapping(value = "/{userName}/todo/remove", method = RequestMethod.GET)
    public void removeTodo(@PathVariable String userName, @RequestParam Long id) {
        User user = userRepository.findUserByName(userName).get();

        todoRepository.delete(id);
    }
}
