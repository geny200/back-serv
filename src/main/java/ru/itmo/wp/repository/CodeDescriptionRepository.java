package ru.itmo.wp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itmo.wp.domain.Code;
import ru.itmo.wp.domain.CodeDescription;

import java.util.List;

public interface CodeDescriptionRepository extends JpaRepository<CodeDescription, Long> {
    @Query(value = "SELECT * FROM code_description WHERE user_id=?1", nativeQuery = true)
    List<CodeDescription> findByUser(long userId);

    @Query(value = "SELECT * FROM code_description WHERE id=?1 AND user_id=?2", nativeQuery = true)
    CodeDescription findById(long codeId, long userId);
}
