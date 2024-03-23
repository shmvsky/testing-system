package ru.shmvsky.testingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.shmvsky.testingsystem.entity.Attempt;
import ru.shmvsky.testingsystem.entity.User;

import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt, Integer> {

    List<Attempt> findAllByTestId(Integer testId);

}
