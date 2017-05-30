package ru.timokhin.todolist;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import ru.timokhin.todolist.dao.TodoRepository;
import ru.timokhin.todolist.dao.UserRepository;
import ru.timokhin.todolist.model.Todo;
import ru.timokhin.todolist.model.User;

@SpringBootApplication
public class TodolistApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodolistApplication.class, args);
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("validation");
        return messageSource;
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, TodoRepository todoRepository) {
        return strings -> {
            User user = userRepository.save(new User("admin", "admin", "ROLE_ADMIN"));

            Todo loginTodo = new Todo("Log in as admin", user);
            loginTodo.setCompleted(true);

            todoRepository.save(loginTodo);
            todoRepository.save(new Todo("Make your first todo", user));
            todoRepository.save(new Todo("Complete your first todo", user));
        };
    }
}
