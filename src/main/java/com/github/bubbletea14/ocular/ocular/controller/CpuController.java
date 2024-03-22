package com.github.bubbletea14.ocular.ocular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
        return cpuService.getCpu();
    }
}
