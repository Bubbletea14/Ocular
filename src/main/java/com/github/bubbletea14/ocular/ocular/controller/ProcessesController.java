package com.github.bubbletea14.ocular.ocular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public void addProcess(@RequestBody Processes process) {
        processesService.addProcess(process);
    }

    @GetMapping("/{pId}")
    public Processes getUserById(@PathVariable Long pId) {
        return processesService.getProcessByPid(pId);
    }

    @PutMapping("/{pId}")
    public void updateProcess(@PathVariable Long pId, @RequestBody Processes process) {
        processesService.updateProcess(pId, process);
    }

    @DeleteMapping("/{pId}")
    public void deleteProcess(@PathVariable Long pId) {
        processesService.deleteProcess(pId);
    }

}
