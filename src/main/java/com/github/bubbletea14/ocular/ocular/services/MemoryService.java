package com.github.bubbletea14.ocular.ocular.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.github.bubbletea14.ocular.ocular.tables.Memory;

@Service
public class MemoryService {

    private final List<Memory> memoryList = new ArrayList<>();
    public MemoryService(){
        memoryList.add(new Memory(1,16.0f,7.4F,8.5F,2667F,53.25F));
        memoryList.add(new Memory(2,8.0f,6F,1.4F,1330F,75F));
    }

    //Get Memory by Id
    public Memory getMemoryById(long id) {
        for (Memory memory : memoryList) {
			if (memory.getId()== id) {
				return memory;
			}
		}
		throw new NoSuchElementException("Memory with ID " + id + " not found"); 
    }

    //Get Memory List
    public List<Memory> getMemory() {
        return memoryList;
    }

    //Add new memory
    public Memory addMemory(Memory memory) {
        memoryList.add(memory);
        return memory;
    }
    
    //Update memory
    public Memory updateMemory(Long id, Memory newMemory) {
        for (Memory memory : memoryList) {
            if (memory.getId() == id) 
                {
                memory.setTotalMemory(newMemory.getTotalMemory());
                memory.setFreeMemory(newMemory.getFreeMemory());
                memory.setUsedMemory(newMemory.getUsedMemory());
                memory.setMemorySpeed(newMemory.getMemorySpeed());
                memory.setMemoryUsagePercentage(newMemory.getMemoryUsagePercentage());
                return memory;
            }
        }
        return null; //If no memory is found
    }

    public boolean deleteMemory(Long id) {
        return memoryList.removeIf(memory -> memory.getId() == id);
    }

}
