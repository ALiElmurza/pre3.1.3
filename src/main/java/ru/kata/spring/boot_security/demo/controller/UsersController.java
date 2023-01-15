//package ru.kata.spring.boot_security.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import ru.kata.spring.boot_security.demo.models.User;
//import ru.kata.spring.boot_security.demo.services.UserService;
//
//@Controller
//@RequestMapping("/")
//public class UsersController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UsersController(UserService userService) {
//        this.userService = userService;
//    }
//
//
//    @GetMapping()
//    public String getUsers(Model model) {
//        model.addAttribute("users", userService.findAll());
//        return "index";
//    }
//
//    @GetMapping("/new")
//    public String addUser(@ModelAttribute("user") User user) {
//        return "new";
//    }
//
//    @PostMapping()
//    public String create(@ModelAttribute("user") User user) {
//        userService.save(user);
//        return "redirect:/user";
//    }
//
//    @GetMapping("/{id}")
//    public String getUserById(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("user", userService.findOne(id));
//        return "show";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("user", userService.findOne(id));
//        return "edit";
//    }
//
//    @PatchMapping("/{id}")
//    public String update(@ModelAttribute("user") User user) {
//        userService.update(user);
//        return "redirect:/user";
//    }
//
//    @GetMapping("/update")
//    public String getListToUpdate(Model model) {
//        model.addAttribute("users", userService.findAll());
//        return "update";
//    }
//
//    @GetMapping("/delete")
//    public String getListToDelete(Model model) {
//        model.addAttribute("users", userService.findAll());
//        return "delete";
//    }
//
//    @GetMapping("/{id}/deleteById")
//    public String getUserToDelete(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("user", userService.findOne(id));
//        return "show";
//    }
//
//    @DeleteMapping("/{id}/delete")
//    public String delete(@PathVariable("id") Long id) {
//        userService.delete(id);
//        return "redirect:/user";
//    }
//
//
//}
