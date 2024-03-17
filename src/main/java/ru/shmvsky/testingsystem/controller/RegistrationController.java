package ru.shmvsky.testingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/register")
public class RegistrationController {

    @GetMapping
    public String showRegistrationForm() {
        return null;
    }

    @PostMapping
    public String processRegistration() {
        return null;
    }


}
