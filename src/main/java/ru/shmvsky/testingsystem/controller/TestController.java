package ru.shmvsky.testingsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shmvsky.testingsystem.entity.Attempt;
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

    @GetMapping("/start")
    public String showTestForm(@PathVariable Integer testId, Model model, Principal principal) {
        Attempt actualAttempt = attemptService.getActualAttempt(userService.getUser(principal.getName()), testService.getTest(testId));

        if (actualAttempt == null) {
            return "redirect:/test/" + testId + "/preview";
        }

        model.addAttribute("userInfo", userService.getUserInfo(principal.getName()));
        model.addAttribute("attempt", actualAttempt);

        return "test/test";
    }

    @PostMapping("/finish")
    public String endTest(@PathVariable Integer testId, @ModelAttribute Attempt attempt) {

        attempt.getAnswers().stream().forEach(answer -> System.out.println(answer.getUserAns()));

        attemptService.processResults(testService.getTest(testId), attempt);

        return "redirect:/attempts/show/" + attempt.getId();
    }

}
