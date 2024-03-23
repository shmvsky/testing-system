package ru.shmvsky.testingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shmvsky.testingsystem.entity.*;
import ru.shmvsky.testingsystem.pojo.AttemptInfo;
import ru.shmvsky.testingsystem.repository.AttemptRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    public List<AttemptInfo> getUserAttemptsInfos(String username) {
        return attemptRepository.findAll().stream()
                .filter(attempt -> attempt.getUser().getUsername().equals(username))
                .map(test -> getAttemptInfo(test.getId()))
                .collect(Collectors.toList());
    }

    public List<Attempt> getUserAttempts(String username) {
        return attemptRepository.findAll().stream()
                .filter(attempt -> attempt.getUser().getUsername().equals(username))
                .collect(Collectors.toList());
    }

    public Attempt getUserAttempt(String username, Integer attemptId) {
        for (Attempt attempt : getUserAttempts(username)) {
            if (attempt.getId() == attemptId) {
                return attempt;
            }
        }
        return null;
    }

    public List<AttemptInfo> getTestAttemptsInfos(Integer testId) {
        return getTestAttempts(testId).stream()
                .map(attempt -> getAttemptInfo(attempt.getId()))
                .collect(Collectors.toList());
    }

    private Attempt getAttempt(Integer attemptId) {
        return attemptRepository.findById(attemptId).orElseThrow(RuntimeException::new);
    }

    private void endAttempt(Attempt attempt, Status status) {
        attempt.setStatus(status);
        attempt.setFinishedAt(LocalDateTime.now());
        attemptRepository.save(attempt);
    }

    private boolean attemptsLimitNotReached(User user, Test test) {
        List<Attempt> attempts = attemptRepository.findUserAttemptsDesc(user.getId(), test.getId());
        return attempts.size() < test.getAttemptsLimit();

    }

    public void processResults(Test test, Attempt attempt) {

        attempt.setStatus(Status.FINISHED);
        attempt.setFinishedAt(LocalDateTime.now());

        List<Question> questions = test.getQuestions();

        assert questions.size() == attempt.getAnswers().size();

        for (int i = 0; i < questions.size(); i++) {
            attempt.getAnswers().get(i).setRight(attempt.getAnswers().get(i).getUserAns().equals(questions.get(i).getAnswer()));
        }

        Attempt savedAttempt = attemptRepository.save(attempt);
        savedAttempt.getAnswers().forEach(answer -> System.out.println(answer.getUserAns()));

    }

    public Attempt getActualAttempt(User user, Test test) {

        Attempt prevAttempt = getPrevAttempt(user, test);

        if (prevAttempt != null) {
            if (prevAttempt.getStatus() == Status.STARTED) {
                if (prevAttempt.getStartedAt().plus(Duration.ofMillis(test.getDuration())).isAfter(LocalDateTime.now())) {
                    return prevAttempt;
                }
                endAttempt(prevAttempt, Status.EXPIRED);
            }
        }
        if (attemptsLimitNotReached(user, test) && test.isOpened()) {

            Attempt attempt = Attempt.builder()
                    .user(user)
                    .test(test)
                    .status(Status.STARTED)
                    .answers(new ArrayList<>())
                    .startedAt(LocalDateTime.now())
                    .build();

            for (int i = 0; i < test.getQuestions().size(); i++) {
                attempt.addAnswer(new Answer());
            }

            return attemptRepository.save(attempt);
        }

        return null;
    }

    public Attempt getPrevAttempt(User user, Test test) {
        List<Attempt> attempts = attemptRepository.findUserAttemptsDesc(user.getId(), test.getId());
        if (!attempts.isEmpty()) {
            return attempts.get(0);
        }
        return null;
    }



}
