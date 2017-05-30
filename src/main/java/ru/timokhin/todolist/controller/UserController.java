package ru.timokhin.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.timokhin.todolist.dao.UserRepository;
import ru.timokhin.todolist.model.User;
import ru.timokhin.todolist.service.SecurityService;
import ru.timokhin.todolist.validation.UserValidator;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private MessageSource messageSource;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> validationError(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String field = fieldError.getField();
        String message = messageSource.getMessage(fieldError.getCode(), null, null);
        return Collections.singletonMap(field, message);
    }

    @PostMapping("/register")
    public User register(@Valid @RequestBody User user) {
        user.setRole("ROLE_USER");
        userRepository.save(user);
        securityService.login(user.getName(), user.getPassword());
        return user;
    }
}
