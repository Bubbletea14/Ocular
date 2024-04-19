package com.github.bubbletea14.ocular.ocular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Memory addMemory(@RequestBody Memory memory) {
       return memoryService.addMemory(memory);
    }

    @PutMapping
    public void updateMemory(@RequestBody Memory memory){
        memoryService.updateMemory(memory);
    }

    @DeleteMapping
    public void deleteMemory(@RequestBody Memory memory) {
        memoryService.deleteMemory(memory);
    }
}
