package com.github.bubbletea14.ocular.ocular.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.bubbletea14.ocular.ocular.tables.Memory;

@Service
public class MemoryService {
    // public List<Memory> getMemory(){
    //     return List.of(new Memory(2.1f, 50.0f));
    // }
    private final List<Memory> memoryList = new ArrayList<>();
    public MemoryService(){
        memoryList.add(new Memory(2.1f, 50.0f));
        memoryList.add(new Memory(1.1f, 30.0f));
        memoryList.add(new Memory(0.1f, 10.1f));
    }

    public List<Memory> getMemory() {
        return memoryList;
    }

    public void addMemory(Memory memory) {
        memoryList.add(memory);
    }
    
    public void updateMemory(Memory newMemory) {
        for (Memory memory : memoryList) {
            if (memory.getMemoryUsage() == newMemory.getMemoryUsage() &&
                memory.getMemorySpeed() == newMemory.getMemorySpeed()) {
                memory.setMemoryUsage(newMemory.getMemoryUsage());
                memory.setMemorySpeed(newMemory.getMemorySpeed());
                break;
            }
        }
    }

    public void deleteMemory(Memory deleteMemory) {
        memoryList.remove(deleteMemory);
    }

}
