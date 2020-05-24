package ru.itmo.wp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itmo.wp.domain.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByOrderByCreationTimeDesc();

    @Query(value = "SELECT * FROM task WHERE id=?1", nativeQuery = true)
    Task findById(long id);
}
