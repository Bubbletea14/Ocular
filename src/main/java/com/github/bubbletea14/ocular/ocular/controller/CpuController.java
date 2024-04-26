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

import com.github.bubbletea14.ocular.ocular.services.CpuService;
import com.github.bubbletea14.ocular.ocular.tables.Cpu;


@RestController
@RequestMapping(path = "api/v1/Cpu")
public class CpuController {
    //Reference
    private final CpuService cpuService;

    @Autowired
    //Constructor
    public CpuController (CpuService cpuService) {
        this.cpuService = cpuService;
    }

    //Retrieve Cpu
    @GetMapping
    public List<Cpu> getCpu() {
        return cpuService.getCpuList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cpu addCpu(@RequestBody Cpu cpu) {
        return cpuService.addCpu(cpu);
    }

    //Retrieve specific CPU Metric
    @GetMapping("/{id}")
    public Optional<Cpu> getCpuById(@PathVariable Long id) {
        return cpuService.getCpuById(id);
    }

    //
    @PutMapping("/{id}")
    public Optional<Cpu> updateCpu(@PathVariable Long id, @RequestBody Cpu cpu){
        return cpuService.updateCpu(id,cpu);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCpu(@PathVariable Long id) {
       return cpuService.deleteCpu(id);
    }
}
