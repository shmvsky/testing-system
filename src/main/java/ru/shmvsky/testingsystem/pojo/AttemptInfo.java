package ru.shmvsky.testingsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shmvsky.testingsystem.entity.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttemptInfo {

    private Integer attemptId;

    private String fullname;

    private String testTitle;

    private Status status;

    private Integer rightAnswersCnt;

    private long timeSpent;

}
