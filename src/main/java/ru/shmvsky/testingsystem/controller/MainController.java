package ru.shmvsky.testingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.shmvsky.testingsystem.pojo.RegistrationForm;
import ru.shmvsky.testingsystem.service.UserService;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String showHomePage(Principal principal, Model model) {
        model.addAttribute("userInfo", userService.getUserInfo(principal.getName()));
        return "main/home";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "main/registration";
    }

    @PostMapping("/registration")
    public String processRegistration(@ModelAttribute RegistrationForm registrationForm) {
        userService.register(registrationForm);
        return "redirect:/login";
    }

}
