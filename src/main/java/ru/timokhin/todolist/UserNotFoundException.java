package ru.timokhin.todolist;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super(String.format("User with name '%s' doesn't exist", username));
    }
}
