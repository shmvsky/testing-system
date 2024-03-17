package ru.shmvsky.testingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shmvsky.testingsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
