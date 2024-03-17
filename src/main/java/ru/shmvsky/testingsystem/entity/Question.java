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
