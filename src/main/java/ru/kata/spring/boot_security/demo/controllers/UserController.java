package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;



    @GetMapping()
    public String userPage(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user/user";
    }


}

