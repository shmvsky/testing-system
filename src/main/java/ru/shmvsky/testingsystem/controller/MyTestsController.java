package ru.shmvsky.testingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import ru.shmvsky.testingsystem.dto.TestDto;
import ru.shmvsky.testingsystem.entity.Answer;
import ru.shmvsky.testingsystem.entity.Test;
import ru.shmvsky.testingsystem.service.AttemptService;
import ru.shmvsky.testingsystem.service.TestService;
import ru.shmvsky.testingsystem.service.UserService;

import java.io.IOException;
import java.security.Principal;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/my-tests")
public class MyTestsController {

    @Autowired
    private UserService userService;

    @Autowired
    private TestService testService;

    @Autowired
    private AttemptService attemptService;

    @GetMapping
    public String showAllTests(Principal principal, Model model) {
        model.addAttribute("userInfo", userService.getUserInfo(principal.getName()));
        model.addAttribute("ownedTests", testService.getOwnedTestsInfos(principal.getName()));
        return "my_tests/owned_tests";
    }

    @GetMapping("/show-details/{testId}")
    public String showTestDetails(@PathVariable Integer testId, Principal principal, Model model) {

        model.addAttribute("userInfo", userService.getUserInfo(principal.getName()));
        model.addAttribute("testInfo", testService.getOwnedTestInfo(principal.getName(), testId));
        model.addAttribute("attempts", attemptService.getTestAttemptsInfos(testId));

        return "my_tests/test_details";
    }

    @GetMapping("/edit/{testId}")
    public String showEditForm(@PathVariable Integer testId, Principal principal, Model model) {

        model.addAttribute("userInfo", userService.getUserInfo(principal.getName()));
        model.addAttribute("testDto", testService.getOwnedTestDto(principal.getName(), testId));
//        model.addAttribute("testDto", testService.getOwnedTest());
        return "my_tests/test_edit";
    }

    @PostMapping("/edit/{testId}/save")
    public String saveChanges(@PathVariable Integer testId, @ModelAttribute TestDto dto, Principal p) {
        Test updatedTest = testService.updateOwnedTest(p.getName(), testId, dto);
        return "redirect:/my-tests/show-details/" + updatedTest.getId();
    }

    @PostMapping("/edit/{testId}/delete")
    public String deleteTest(@PathVariable String testId) {
        return null;
    }

}
