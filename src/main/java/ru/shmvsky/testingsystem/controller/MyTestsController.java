package ru.shmvsky.testingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/my-tests")
public class MyTestsController {

    @GetMapping
    public String showAllTests() {
        return null;
    }

    @PostMapping("/create")
    public String createTest() {
        return null;
    }

    @GetMapping("/edit/{testId}")
    public String showEditForm(@PathVariable String testId) {
        return null;
    }

    @PostMapping("/edit/{testId}/save")
    public String saveChanges(@PathVariable String testId) {
        return null;
    }

    @PostMapping("/edit/{testId}/delete")
    public String deleteTest(@PathVariable String testId) {
        return null;
    }

}
