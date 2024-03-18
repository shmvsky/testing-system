package ru.shmvsky.testingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Question> questions;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Attempt> attempts;

    private String title;

    private String description;

    private int attemptsLimit;

    private boolean isOpened;

    private long duration;

    public void addQuestion(Question question) {
        questions.add(question);
        question.setTest(this);
    }

    public void addAttempt(Attempt attempt) {
        attempts.add(attempt);
        attempt.setTest(this);
    }

    public void deleteQuestion(Question question) {
        questions.remove(question);
        question.setTest(null);
    }

    public void deleteAttempt(Attempt attempt) {
        attempts.remove(attempt);
        attempt.setTest(null);
    }

}
