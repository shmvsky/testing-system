package ru.shmvsky.testingsystem.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@Getter
@Setter
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

    private boolean isRight;

}
