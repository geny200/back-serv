package ru.itmo.wp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itmo.wp.domain.Code;
import ru.itmo.wp.domain.ExecutableCode;
import ru.itmo.wp.domain.User;

import java.util.List;

public interface ExecutableCodeRepository extends JpaRepository<ExecutableCode, Long> {
    List<ExecutableCode> findAllByOrderByCreationTimeDesc();

    @Query(value = "SELECT * FROM executable_code WHERE id=?1", nativeQuery = true)
    ExecutableCode findById(long login);
}
