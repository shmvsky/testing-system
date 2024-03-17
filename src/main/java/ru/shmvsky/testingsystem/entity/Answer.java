package ru.shmvsky.testingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "attempt_id")
    private Attempt attempt;

    private String userAns;

    private String points;

    private boolean isRight;

}
