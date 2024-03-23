package ru.shmvsky.testingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shmvsky.testingsystem.service.AttemptService;
import ru.shmvsky.testingsystem.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/attempts")
public class AttemptsController {

    @Autowired
    private UserService userService;

    @Autowired
    private AttemptService attemptService;

    @GetMapping
    public String showAllAttempts(Principal principal, Model model) {

        model.addAttribute("userInfo", userService.getUserInfo(principal.getName()));
        model.addAttribute("attempts", attemptService.getUserAttempts(principal.getName()));

        return "attempts/my_attempts";
    }

    @GetMapping("/show/{attemptId}")
    public String showAttempt(@PathVariable String attemptId) {
        return null;
    }

}
