package com.github.bubbletea14.ocular.ocular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.bubbletea14.ocular.ocular.services.MemoryService;
import com.github.bubbletea14.ocular.ocular.tables.Memory;

@RestController
@RequestMapping(path = "api/v1/Memory")
public class MemoryController {
    // Reference
    private final MemoryService memoryService;

    @Autowired
    public MemoryController(MemoryService memoryService){
        this.memoryService = new MemoryService();
    }

    @GetMapping
    public List<Memory> getMemory() {
        return memoryService.getMemory();
    }
}
