package ru.shmvsky.testingsystem.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/{testId}")
public class TestController {

    @GetMapping("/preview")
    public String showTestPreview(@PathVariable String testId) {
        return null;
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
