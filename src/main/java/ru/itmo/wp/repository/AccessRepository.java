package ru.itmo.wp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.wp.domain.Access;
import ru.itmo.wp.domain.CodeLink;

public interface AccessRepository extends JpaRepository<Access, Long> {
    @Query(value = "SELECT * FROM access WHERE name=?1", nativeQuery = true)
    Access findByName(String name);
}
