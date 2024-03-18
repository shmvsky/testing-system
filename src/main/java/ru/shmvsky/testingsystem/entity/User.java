package ru.shmvsky.testingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@Table(name = "usr")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String fullname;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder.Default
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Test> ownedTests = new ArrayList<>();

    public void addTest(Test test) {
        ownedTests.add(test);
        test.setAuthor(this);
    }

    public void deleteTest(Test test) {
        ownedTests.remove(test);
        test.setAuthor(null);
    }

}
