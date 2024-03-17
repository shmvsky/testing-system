package ru.shmvsky.testingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/attempts")
public class AttemptsController {

    @GetMapping
    public String showAllAttempts() {
        return null;
    }

    @GetMapping("/show/{attemptId}")
    public String showAttempt(@PathVariable String attemptId) {
        return null;
    }

}
