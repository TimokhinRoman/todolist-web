package ru.timokhin.todolist.dao;

import org.springframework.data.repository.CrudRepository;
import ru.timokhin.todolist.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByName(String name);
}
