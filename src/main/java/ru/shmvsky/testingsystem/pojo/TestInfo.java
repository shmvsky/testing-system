package ru.shmvsky.testingsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shmvsky.testingsystem.entity.Test;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestInfo {

    private long id;

    private String title;

    private String description;

    private long duration;

    private long attemptsLimit;

    private long questionsCnt;

    private boolean isOpened;

}
