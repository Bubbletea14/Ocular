package com.github.bubbletea14.ocular.ocular.tables;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProcessesRepository extends JpaRepository<Processes, Long> {
    Processes findByDateTime(LocalDateTime dateTime);
    List<Processes> findAllByDateTimeBetween(LocalDateTime starTime,LocalDateTime endTime);
    Processes findFirstByOrderByDateTimeDesc();
    List<Processes> findByDateTimeBefore(LocalDateTime thresholdTime);
    Optional<Processes> findBypId(long pId);
}
