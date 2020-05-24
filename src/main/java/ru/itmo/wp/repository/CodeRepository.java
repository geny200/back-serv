package ru.itmo.wp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.wp.domain.Code;

public interface CodeRepository extends JpaRepository<Code, Long> {
}
