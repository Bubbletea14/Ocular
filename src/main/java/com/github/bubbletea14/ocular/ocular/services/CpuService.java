package com.github.bubbletea14.ocular.ocular.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.bubbletea14.ocular.ocular.tables.Cpu;
import java.time.LocalDateTime;
import java.time.Month;

@Service
public class CpuService {
    public List <Cpu> getCpu(){
        return List.of(
            new Cpu(
            "AMD",
            10,
            5L,
            LocalDateTime.of(2024, Month.MARCH, 22, 12, 0)
        ));
    }
}
