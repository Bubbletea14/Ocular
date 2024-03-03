package com.mscs.project.ocular.ocular.Tables;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface CPURepository extends JpaRepository<CPU, LocalDateTime> {
    List<CPU> findByDateTime(LocalDateTime dateTime);
    Optional<CPU> findFirstByOrderByDatetimeDesc();
}
