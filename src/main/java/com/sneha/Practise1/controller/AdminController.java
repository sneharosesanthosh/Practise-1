package com.sneha.Practise1.controller;

import com.sneha.Practise1.entity.Admin;
import com.sneha.Practise1.service.AdminService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
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

    @PostMapping("/logout1")
    public String logout(HttpSession session) {

        try {
            boolean isEmpty = !session.getAttributeNames().hasMoreElements();

            if (isEmpty) {
                return "Session is empty.";
            } else {
                String username = (String) session.getAttribute("username");
                log.info("Before deletion: "+username);
                session.invalidate();
                log.info("After Deletion: ",session.getAttribute("username"));  // causes IllegalStateException
                return "Session deleted.";
            }
        } catch (IllegalStateException e) {
            return "Session is invalid.";
        }
    }
}
