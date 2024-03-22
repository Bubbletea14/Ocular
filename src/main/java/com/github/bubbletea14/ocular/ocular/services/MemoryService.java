package com.github.bubbletea14.ocular.ocular.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.bubbletea14.ocular.ocular.tables.Memory;

@Service
public class MemoryService {
    public List<Memory> getMemory(){
        return List.of(new Memory(2.1f, 50.0f));
    }
}
