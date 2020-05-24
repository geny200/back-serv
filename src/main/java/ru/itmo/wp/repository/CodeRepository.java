package ru.itmo.wp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itmo.wp.domain.Code;
import ru.itmo.wp.domain.ExecutableCode;

import java.util.Date;
import java.util.List;

public interface CodeRepository extends JpaRepository<Code, Long> {
    @Query(value = "SELECT * FROM code WHERE id=?1", nativeQuery = true)
    Code findById(long id);
}
