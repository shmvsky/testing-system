package ru.shmvsky.testingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.shmvsky.testingsystem.entity.Test;
import ru.shmvsky.testingsystem.entity.User;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Integer> {
}
