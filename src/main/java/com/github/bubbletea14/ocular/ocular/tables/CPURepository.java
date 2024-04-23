package com.github.bubbletea14.ocular.ocular.tables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CPURepository extends JpaRepository<Cpu, Long> {
    Cpu findByDateTime(LocalDateTime dateTime);
    Optional<Cpu> findFirstByOrderByDateTimeDesc();
}
