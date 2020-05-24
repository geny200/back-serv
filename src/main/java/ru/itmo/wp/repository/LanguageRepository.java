package ru.itmo.wp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itmo.wp.domain.Language;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    @Query(value = "SELECT * FROM language WHERE name=?1", nativeQuery = true)
    Language findByName(String name);
}
