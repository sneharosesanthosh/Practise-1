package com.sneha.Practise1.controller;

import com.sneha.Practise1.entity.User;
import com.sneha.Practise1.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public User saveUser(@RequestBody User user) {
        return userService.saveuser(user);
    }


}
