package com.example.employeemanagement.controller;

import com.example.employeemanagement.service.UtilityService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final UtilityService utilityService;
    private final PasswordEncoder passwordEncoder;

    // Constructor Injection — Spring tự động truyền bean vào đây
    public HelloController(UtilityService utilityService, PasswordEncoder passwordEncoder) {
        this.utilityService = utilityService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Employee Management System is running!";
    }

    @GetMapping("/test-bean")
    public String testBean() {
        String code = utilityService.generateEmployeeCode();
        String hashed = passwordEncoder.encode("123456");
        return "Mã NV: " + code + " | Password mã hóa: " + hashed;
    }
}