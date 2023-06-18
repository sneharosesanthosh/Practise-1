package com.sneha.Practise1.controller;

import com.sneha.Practise1.entity.User;
import com.sneha.Practise1.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public String saveUser(@RequestBody User user, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            userService.saveUser(user);
            return "USER CREATED BY ADMIN";
        } else {
            return "ADMIN NOT FOUND, ADMIN NEEDS TO LOGIN";
        }

    }

    @GetMapping("/get-users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete-user/{id}")
    public String deleteUserById(@PathVariable Long id, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            userService.deleteUserById(id);
            return "USER DELETED BY ADMIN";
        } else {
            return "ADMIN NOT FOUND, ADMIN NEEDS TO LOGIN";
        }
    }

}
