package com.mscs.project.ocular.ocular.Tables;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProcessesRepository extends JpaRepository<Processes, LocalDateTime> {
    List<Processes> findByDateTime(LocalDateTime dateTime);
    List<Processes> findAllByDateTimeBetween(LocalDateTime starTime,LocalDateTime endTime);
    Optional<Processes> findFirstByOrderByDateTimeDesc();
}
