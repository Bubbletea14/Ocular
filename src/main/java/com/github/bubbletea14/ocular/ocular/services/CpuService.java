package com.github.bubbletea14.ocular.ocular.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.github.bubbletea14.ocular.ocular.tables.Cpu;

import java.time.LocalDateTime;
import java.time.Month;

@Service
public class CpuService {

    private final List<Cpu> cpuList = new ArrayList<>();

    //Insert fake Cpu data
    public CpuService () {
        cpuList.add(new Cpu(
                    1,
                    "AMD",
                    10,
                    4L,
                    LocalDateTime.of(2024, Month.MARCH, 22, 12, 0)));
        cpuList.add(new Cpu(
                    2,
                    "Nvidia",
                    9,
                    5L,
                    LocalDateTime.of(2024, Month.MARCH, 23, 11, 1)));
    }

    //Get cpu by Id
    public Cpu getCpuById(Long id){
        for (Cpu cpu : cpuList) {
			if (cpu.getId()==id) {
				return cpu;
			}
		}
		throw new NoSuchElementException("CPU with ID " + id + " not found"); 
    }

    //Get cpu list
    public List<Cpu> getCpuList() {
        return cpuList;
    }

    //Add Cpu
    public Cpu addCpu(Cpu cpu) {
        cpuList.add(cpu);
        return cpu;
    }

    //Update Cpu(Assume Cpu name is unqiue)
    public Cpu updateCpu(Long id, Cpu newCpu) {
        for (Cpu cpu : cpuList) {
            if (cpu.getId() == id) {
                cpu.setProcessorType(newCpu.getProcessorType());
                cpu.setProcessorSpeed(newCpu.getProcessorSpeed());
                cpu.setCount((long) newCpu.getCount());
                cpu.setUpTime(newCpu.getUpTime());
                return cpu;
            }
        }
        return null; //If no cpu with the given ID is found
    }

    //Delete Cpu
    public boolean deleteCpu(Long id) {
       return cpuList.removeIf(cpu -> cpu.getId() == id);
    }
}
