package com.github.bubbletea14.ocular.ocular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.bubbletea14.ocular.ocular.services.ProcessesService;
import com.github.bubbletea14.ocular.ocular.tables.Processes;

@RestController
@RequestMapping(path = "api/v1/Processes")
public class ProcessesController {

    // Reference
    private final ProcessesService processesService;

    @Autowired
    // Constructor
    public ProcessesController(ProcessesService processesService){
        this.processesService = new ProcessesService();
    }

    @GetMapping
    public List<Processes> getProcesses() {
        return processesService.getProcesses();
    }
}
