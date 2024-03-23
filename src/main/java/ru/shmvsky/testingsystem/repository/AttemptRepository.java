package ru.shmvsky.testingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.shmvsky.testingsystem.entity.Attempt;

import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt, Integer> {

    List<Attempt> findAllByTestId(Integer testId);

    @Query(value = "SELECT * FROM Attempt a WHERE a.user_id = ?1 AND a.test_id = ?2 ORDER BY a.started_at DESC", nativeQuery = true)
    List<Attempt> findUserAttemptsDesc(Integer userId, Integer testId);

}
