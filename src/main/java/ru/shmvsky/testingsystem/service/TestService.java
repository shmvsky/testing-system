package ru.shmvsky.testingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shmvsky.testingsystem.dto.TestDto;
import ru.shmvsky.testingsystem.entity.Test;
import ru.shmvsky.testingsystem.pojo.TestInfo;
import ru.shmvsky.testingsystem.repository.TestRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    private Test getOwnedTest(String username, Integer testId) {

        List<Test> ownedTests = getOwnedTests(username).stream()
                .filter(t -> t.getId() == testId)
                .toList();

        assert ownedTests.size() <= 1;

        if (ownedTests.size() == 1) {
            return ownedTests.get(0);
        } else {
            throw new RuntimeException("Test not found with id " + testId);
        }

    }

    public Test getTest(Integer testId) {
        return testRepository.findById(testId).orElseThrow();
    }

    private List<Test> getOwnedTests(String username) {
        return testRepository.findAll().stream()
                .filter(t -> t.getAuthor().getUsername().equals(username))
                .collect(Collectors.toList());
    }

    public List<TestInfo> getOwnedTestsInfos(String username) {
        return getOwnedTests(username).stream().map(this::getTestInfo).collect(Collectors.toList());
    }

    public TestInfo getOwnedTestInfo(String username, Integer testId) {
        return getTestInfo(getOwnedTest(username, testId));
    }

    public TestDto getOwnedTestDto(String username, Integer testId) {
        return new TestDto(getOwnedTest(username, testId));
    }

    public TestInfo getTestInfo(Test test) {
        return new TestInfo(
                test.getId(),
                test.getTitle(),
                test.getDescription(),
                test.getDuration(),
                test.getAttemptsLimit(),
                test.getQuestions().size(),
                test.isOpened()
        );
    }

    public TestInfo getTestInfo(Integer testId) {

       Test test =  testRepository.findById(testId).orElseThrow();

        return new TestInfo(
                test.getId(),
                test.getTitle(),
                test.getDescription(),
                test.getDuration(),
                test.getAttemptsLimit(),
                test.getQuestions().size(),
                test.isOpened()
        );
    }

    public Test updateOwnedTest(String username, Integer testId, TestDto dto) {
        Test ownedTest = getOwnedTest(username, testId);

        ownedTest.setTitle(dto.getTitle());
        ownedTest.setDescription(dto.getDescription());
        ownedTest.setAttemptsLimit(dto.getMaxAttempts());
        ownedTest.setOpened(dto.getIsOpened());
        ownedTest.setDuration(dto.getDuration());

        ownedTest.updateQuestions(dto.getQuestions());

        return testRepository.save(ownedTest);
    }

}
