package ru.timokhin.todolist.dao;

import org.springframework.data.repository.CrudRepository;
import ru.timokhin.todolist.bean.User;

import java.util.Optional;

/**
 * Created by Roman on 10.04.2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByName(String name);
}
