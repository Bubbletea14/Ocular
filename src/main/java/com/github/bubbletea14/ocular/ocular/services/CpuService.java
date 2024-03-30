package com.github.bubbletea14.ocular.ocular.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.bubbletea14.ocular.ocular.tables.Cpu;
import com.github.bubbletea14.ocular.ocular.tables.Memory;

import java.time.LocalDateTime;
import java.time.Month;

@Service
public class CpuService {
    // public List <Cpu> getCpu(){
    //     return List.of(
    //         new Cpu(
    //         "AMD",
    //         10,
    //         5L,
    //         LocalDateTime.of(2024, Month.MARCH, 22, 12, 0)
    //     ));
    // }
    private final List<Cpu> cpuList = new ArrayList<>();

    //Insert fake Cpu data
    public CpuService () {
        cpuList.add(new Cpu(
                    "AMD",
                    10,
                    5L,
                    LocalDateTime.of(2024, Month.MARCH, 22, 12, 0)));
        cpuList.add(new Cpu(
                    "Nvidia",
                    10,
                    5L,
                    LocalDateTime.of(2024, Month.MARCH, 23, 11, 1)));
    }

    //Get the cpu list
    public List<Cpu> getCpuList() {
        return cpuList;
    }

    //Add Cpu
    public void addCpu(Cpu cpu) {
        cpuList.add(cpu);
    }

    //Update Cpu(Assume Cpu name is unqiue)
    public void updateCpu(Cpu newCpu) {
        for (Cpu cpu : cpuList) {
            if (cpu.getProcessorType() == (newCpu.getProcessorType())) {
                cpu.setProcessorType(newCpu.getProcessorType());
                cpu.setProcessorSpeed(newCpu.getProcessorSpeed());
                cpu.setCount((long) newCpu.getCount());
                cpu.setUpTime(newCpu.getUptime());
                break;
            }
        }
    }

    //Delete Cpu
    public void deleteCpu(Cpu cpu) {
        cpuList.remove(cpu);
    }
}
