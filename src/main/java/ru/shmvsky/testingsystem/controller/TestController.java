package ru.shmvsky.testingsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shmvsky.testingsystem.repository.UserRepository;
import ru.shmvsky.testingsystem.service.AttemptService;
import ru.shmvsky.testingsystem.service.TestService;
import ru.shmvsky.testingsystem.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/test/{testId}")
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private TestService testService;

    @Autowired
    private AttemptService attemptService;

    @GetMapping("/preview")
    public String showTestPreview(@PathVariable Integer testId, Principal principal, Model model) {
        model.addAttribute("userInfo", userService.getUserInfo(principal.getName()));
        model.addAttribute("testInfo", testService.getTestInfo(testId));
        return "test/test_preview";
    }

    @PostMapping("/start")
    public String startTest(@PathVariable String testId) {
        return null;
    }

    @GetMapping("/start")
    public String showTestForm(@PathVariable String testId) {
        return null;
    }

    @PostMapping("/finish")
    public String endTest(@PathVariable String testId) {
        return null;
    }

}
