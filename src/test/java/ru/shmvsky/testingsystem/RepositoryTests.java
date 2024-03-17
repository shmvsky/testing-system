package ru.shmvsky.testingsystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.shmvsky.testingsystem.entity.User;
import ru.shmvsky.testingsystem.repository.TestRepository;
import ru.shmvsky.testingsystem.repository.UserRepository;

import java.util.ArrayList;

@DataJpaTest
public class RepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRepository testRepository;

    @Test
    void shouldPersistUser() {
        User user = User.builder()
                .username("username")
                .password("password")
                .fullname("Firstname MiddleName Surname")
                .build();

        userRepository.save(user);

        User savedUser = userRepository.findById(user.getId()).orElseThrow();

        Assertions.assertEquals(user.getId(), savedUser.getId());
    }

    @Test
    void shouldPersistUserWithTests() {
        User user = User.builder()
                .username("username2")
                .password("password")
                .fullname("Firstname MiddleName Surname")
                .build();

        Assertions.assertEquals(user.getOwnedTests(), new ArrayList<>());

        user.addTest(
                ru.shmvsky.testingsystem.entity.Test.builder()
                        .title("Test")
                        .description("description")
                        .attemptsLimit(3)
                        .isOpened(true)
                        .duration(60000)
                        .build()
        );

        userRepository.save(user);

        User savedUser = userRepository.findById(user.getId()).orElseThrow();

        Assertions.assertEquals(user.getId(), savedUser.getId());

        savedUser.getOwnedTests().forEach(test -> {
            ru.shmvsky.testingsystem.entity.Test savedTest = testRepository.findById(test.getId()).orElseThrow();
            Assertions.assertEquals(test.getId(), savedTest.getId());
        });

    }

}
