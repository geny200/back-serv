package ru.itmo.wp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.wp.domain.CodeLink;
import ru.itmo.wp.domain.Task;

import java.util.List;

public interface LinkCodeRepository extends JpaRepository<CodeLink, Long> {
    @Query(value = "SELECT * FROM code_link WHERE link=?1", nativeQuery = true)
    CodeLink findByLink(String link);

    @Transactional
    @Modifying
    @Query(value = "UPDATE code_link SET views=?2 WHERE link=?1", nativeQuery = true)
    void updateViews(String link, long views);
}
