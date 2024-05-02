package com.github.bubbletea14.ocular.ocular.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.bubbletea14.ocular.ocular.tables.Memory;
import com.github.bubbletea14.ocular.ocular.tables.MemoryRepository;

@Service
public class MemoryService {

    private final MemoryRepository memoryRepository;

    @Autowired
    public MemoryService(MemoryRepository memoryRepository) {
        this.memoryRepository = memoryRepository;
    }

    // Get Memory List
    public List<Memory> getMemory() {
        return memoryRepository.findAll();
    }

    //Get Memory by Id
    public Optional<Memory> getMemoryById(long id) {
        return memoryRepository.findById(id);
    }

    //Add new memory
    public Memory addMemory(Memory memory) {
        return memoryRepository.save(memory);
    }
    
    //Update memory
    public Optional<Memory> updateMemory(Long id, Memory newMemory) {
        return memoryRepository.findById(id).map(existingMemory -> {
            existingMemory.setTotalMemory(newMemory.getTotalMemory());
            existingMemory.setFreeMemory(newMemory.getFreeMemory());
            existingMemory.setUsedMemory(newMemory.getUsedMemory());
            existingMemory.setMemorySpeed(newMemory.getMemorySpeed());
            existingMemory.setMemoryUsagePercentage(newMemory.getMemoryUsagePercentage());
            return memoryRepository.save(existingMemory);
        });
    }

    public boolean deleteMemory(Long id) {
        if (memoryRepository.existsById(id)) {
            memoryRepository.deleteById(id);
        }
        return true;
    }

}
