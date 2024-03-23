package ru.shmvsky.testingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shmvsky.testingsystem.entity.Answer;
import ru.shmvsky.testingsystem.entity.Attempt;
import ru.shmvsky.testingsystem.pojo.AttemptInfo;
import ru.shmvsky.testingsystem.repository.AttemptRepository;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttemptService {

    @Autowired
    private AttemptRepository attemptRepository;

    private List<Attempt> getTestAttempts(Integer testId) {
        return attemptRepository.findAllByTestId(testId);
    }

    public AttemptInfo getAttemptInfo(Integer attemptId) {
        Attempt attempt = getAttempt(attemptId);

        return new AttemptInfo(
                attemptId,
                attempt.getUser().getFullname(),
                attempt.getTest().getTitle(),
                attempt.getStatus(),
                (int) attempt.getAnswers().stream().filter(Answer::isRight).count(),
                 attempt.getStartedAt().until(attempt.getFinishedAt(), ChronoUnit.MILLIS)
        );
    }

    public List<AttemptInfo> getUserAttempts(String username) {
        return attemptRepository.findAll().stream()
                .filter(attempt -> attempt.getUser().getUsername().equals(username))
                .map(test -> getAttemptInfo(test.getId()))
                .collect(Collectors.toList());
    }

    public List<AttemptInfo> getTestAttemptsInfos(Integer testId) {
        return getTestAttempts(testId).stream()
                .map(attempt -> getAttemptInfo(attempt.getId()))
                .collect(Collectors.toList());
    }

    private Attempt getAttempt(Integer attemptId) {
        return attemptRepository.findById(attemptId).orElseThrow(RuntimeException::new);
    }

}
