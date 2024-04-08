package com.github.bubbletea14.ocular.ocular.tables;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface CPURepository extends JpaRepository<Cpu, LocalDateTime> {
    List<Cpu> findByDateTime(LocalDateTime dateTime);
    Optional<Cpu> findFirstByOrderByDateTimeDesc();
}
