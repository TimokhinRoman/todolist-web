package ru.timokhin.todolist.dao;

import org.springframework.data.repository.CrudRepository;
import ru.timokhin.todolist.model.Todo;

import java.util.Collection;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    Collection<Todo> findByUserName(String name);
}
