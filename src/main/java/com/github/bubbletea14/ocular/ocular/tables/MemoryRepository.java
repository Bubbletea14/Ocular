package com.github.bubbletea14.ocular.ocular.tables;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryRepository extends JpaRepository<Memory,LocalDateTime>{
   List<Memory> findAllByDateTimeAfter(LocalDateTime dateTime);
   Optional<Memory> findFirstByOrderByDateTimeDesc();
   Memory findByDateTime(LocalDateTime dateTime);
}
