package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String adminPage(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());;
        model.addAttribute("user", user);
        model.addAttribute("users", userService.findAll());
        return "admin/admin";
    }

    @GetMapping("/delete")
    public String getListToDelete(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/delete";
    }



}
