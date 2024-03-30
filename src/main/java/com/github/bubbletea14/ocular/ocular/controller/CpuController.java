package com.github.bubbletea14.ocular.ocular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.bubbletea14.ocular.ocular.services.CpuService;
import com.github.bubbletea14.ocular.ocular.tables.Cpu;


@RestController
@RequestMapping(path = "api/v1/Cpu")
public class CpuController {
    private final CpuService cpuService;

    @Autowired
    public CpuController (CpuService cpuService) {
        this.cpuService = cpuService;
    }

    @GetMapping
    public List<Cpu> getCpu() {
        return cpuService.getCpuList();
    }

    @PostMapping
    public void addCpu(@RequestBody Cpu cpu){
        cpuService.addCpu(cpu);
    }

    @PutMapping
    public void updateCpu(@RequestBody Cpu cpu){
        cpuService.updateCpu(cpu);
    }

    @DeleteMapping
    public void deleteCpu(@RequestBody Cpu cpu) {
        cpuService.deleteCpu(cpu);
    }
}
