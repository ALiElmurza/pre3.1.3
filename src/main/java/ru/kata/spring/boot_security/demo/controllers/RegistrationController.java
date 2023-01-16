package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    @GetMapping("/")
    public String registrationPage() {
        return "index";
    }

    @GetMapping("/authenticated")
    public String authenticatedPage() {
        return "authenticated";
    }
}
