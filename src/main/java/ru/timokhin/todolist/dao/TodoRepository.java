package ru.timokhin.todolist.dao;

import org.springframework.data.repository.CrudRepository;
import ru.timokhin.todolist.bean.Todo;

import java.util.Collection;

/**
 * Created by Roman on 10.04.2017.
 */
public interface TodoRepository extends CrudRepository<Todo, Long> {
    Collection<Todo> findByUserName(String name);
}
