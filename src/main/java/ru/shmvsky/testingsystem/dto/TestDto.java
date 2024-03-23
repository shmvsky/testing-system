package ru.shmvsky.testingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shmvsky.testingsystem.entity.Question;
import ru.shmvsky.testingsystem.entity.Test;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDto {

    private Integer testId;

    private String title;

    private String description;

    private Integer maxAttempts;

    private Boolean isOpened;

    private long duration;

    private List<Question> questions;

    public TestDto(Test test) {
        this(test.getId(), test.getTitle(), test.getDescription(), test.getAttemptsLimit(), test.isOpened(), test.getDuration(), test.getQuestions());
    }

}