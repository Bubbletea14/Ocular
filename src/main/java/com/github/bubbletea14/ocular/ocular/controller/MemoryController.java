package com.github.bubbletea14.ocular.ocular.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // Constructor 
    @Autowired
    public MemoryController(MemoryService memoryService){
        this.memoryService = memoryService;
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

    @GetMapping("/{id}")
    public Optional<Memory> getMemoryById(@PathVariable Long id) {
        return memoryService.getMemoryById(id);
    }

    @PutMapping("/{id}")
    public Optional<Memory> updateMemory(@PathVariable Long id,@RequestBody Memory memory){
       return memoryService.updateMemory(id,memory);
    }

    @DeleteMapping("/{id}")
    public boolean deleteMemory(@PathVariable Long id) {
        return memoryService.deleteMemory(id);
    }
}
