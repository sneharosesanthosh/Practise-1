package com.sneha.Practise1.controller;

import com.sneha.Practise1.entity.Admin;
import com.sneha.Practise1.entity.User;
import com.sneha.Practise1.service.AdminService;
import com.sneha.Practise1.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {


    private final AdminService adminService;
    private final UserService userService;

    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @PostMapping("/create-admin")
    public String saveAdmin(@RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }

    @GetMapping("/get-admins")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @PostMapping("/login-admin")
    public ResponseEntity<String> loginAdmin(@RequestBody Admin admin, HttpSession session) {
        Admin admin_returned = adminService.loginAdmin(admin);
        if (admin_returned != null) {
            session.setAttribute("username", admin_returned.getUsername());
            return ResponseEntity.ok("LOGGED IN");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("INCORRECT PASSWORD/USERNAME");
        }
    }

    @PostMapping("/create-user")
    public String  saveUser(@RequestBody User user, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            userService.saveuser(user);
            return "USER CREATED BY ADMIN";
        }
        else {
            return "ADMIN NOT FOUND, ADMIN NEEDS TO LOGIN";
        }

    }

}
