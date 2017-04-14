package ru.timokhin.todolist.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.timokhin.todolist.bean.User;
import ru.timokhin.todolist.dao.UserRepository;

@RestController
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "Pizdec!";
        //System.out.println("login: Request method 'GET'");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestBody User user) {
        logger.info(user.toString());
    }

    @RequestMapping(value = "/request/register", method = RequestMethod.POST)
    public void register(@RequestBody User user) {
        System.out.println(user);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout() {

    }
}
