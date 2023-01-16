package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class RegistrationController {

    @GetMapping
    public ModelAndView registrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
//
//    @GetMapping("/authenticated")
//    public String authenticatedPage(Principal principal) {
//        return "authenticated: " + principal.getName();
//    }

