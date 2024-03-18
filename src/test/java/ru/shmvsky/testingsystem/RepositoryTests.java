package ru.shmvsky.testingsystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.shmvsky.testingsystem.entity.User;
import ru.shmvsky.testingsystem.repository.TestRepository;
import ru.shmvsky.testingsystem.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class RepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRepository testRepository;

    @Test
    void shouldFindPopulatedUser() {
        List<User> users = userRepository.findAll();
        Assertions.assertEquals(users.size(), 1);
    }

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
    void shouldAddTestToUser() {
        User user = userRepository.findById(1).orElseThrow();

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

        User savedUser = userRepository.findById(1).orElseThrow();

        Assertions.assertEquals(savedUser.getOwnedTests().size(), 1);
    }



}
