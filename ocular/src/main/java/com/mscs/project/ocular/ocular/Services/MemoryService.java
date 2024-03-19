package com.mscs.project.ocular.ocular.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mscs.project.ocular.ocular.Tables.*;

@Service
public class MemoryService {
    private final MemoryRepository memoryRepository;

    @Autowired
    public MemoryService(MemoryRepository memoryRepository) {
        this.memoryRepository = memoryRepository;
    }

    public Optional<Memory> findMostUpToDateMemory() {
        return memoryRepository.findFirstByOrderByDateTimeDesc();
    }
}
