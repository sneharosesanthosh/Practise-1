package com.sneha.Practise1.controller;

import com.sneha.Practise1.entity.User;
import com.sneha.Practise1.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> saveUser(@RequestBody User user, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            userService.saveUser(user);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "USER CREATED");
            response.put("data", user);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ADMIN NOT FOUND, ADMIN NEEDS TO LOGIN");
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

    @GetMapping("/get-user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            User user = userService.getUserById(id);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("USER NOT FOUND");
            }
        } else {
            String errorMessage = "ADMIN NOT FOUND, ADMIN NEEDS TO LOGIN";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
        }
    }
    // return ResponseEntity.ok().build();    // MAGIC IN HERE
}
