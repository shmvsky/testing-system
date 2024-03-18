package ru.shmvsky.testingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    private String body;
    private String answer;
    private int points;

}
