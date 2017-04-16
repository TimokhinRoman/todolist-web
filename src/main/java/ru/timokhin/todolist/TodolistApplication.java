package ru.timokhin.todolist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
public class TodolistApplication {

    private static final Logger logger = LoggerFactory.getLogger(TodolistApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TodolistApplication.class, args);
    }

/*    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, TodoRepository todoRepository) {
        return strings -> {
            User user = userRepository.save(new User("user", "123"));

//            todoRepository.save(new Todo("111111111111111111111", user));
//            todoRepository.save(new Todo("222222222222222222222", user));
//            todoRepository.save(new Todo("333333333333333333333", user));
//
//            logger.info(userRepository.findOne(1L).toString());
//
//            for (Todo todo : todoRepository.findAll()) {
//                logger.info(todo.toString());
//            }
        };
    }*/
}
