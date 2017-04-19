package ru.timokhin.todolist.validation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.timokhin.todolist.dao.UserRepository;
import ru.timokhin.todolist.model.User;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
        if (user.getName() != null && (user.getName().length() < 3 || user.getName().length() > 16)) {
            errors.rejectValue("name", "size.username");
        }

        if (userRepository.findUserByName(user.getName()) != null) {
            errors.rejectValue("name", "duplicate.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
        if (user.getPassword() != null && (user.getPassword().length() < 5 || user.getPassword().length() > 32)) {
            errors.rejectValue("password", "size.password");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required");
        if (user.getConfirmPassword() != null && !user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "mismatch.password");
        }
    }
}
