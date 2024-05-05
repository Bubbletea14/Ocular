package com.github.bubbletea14.ocular.ocular.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.bubbletea14.ocular.ocular.tables.CPURepository;
import com.github.bubbletea14.ocular.ocular.tables.Cpu;

import java.time.LocalDateTime;
import java.time.Month;

@Service
public class CpuService {

    private final CPURepository cpuRepository;
    
    @Autowired
    public CpuService(CPURepository cpuRepository){
        this.cpuRepository = cpuRepository;
    }

    // Get cpu list
    public List<Cpu> getCpuList() {
        return cpuRepository.findAll();
    }

    // Get Newest update CPU information
    public Optional<Cpu> getNewestCpu(){
        return cpuRepository.findFirstByOrderByDateTimeDesc();
    }

    // Get cpu by Id
    public Optional<Cpu> getCpuById(Long id){
        return cpuRepository.findById(id);
    }

    //Add Cpu
    public Cpu addCpu(Cpu cpu) {
        return cpuRepository.save(cpu);
    }

    //Update Cpu
    public Optional<Cpu> updateCpu(Long id, Cpu newCpu) {
        return cpuRepository.findById(id).map(existingCpu -> {
            existingCpu.setProcessorType(newCpu.getProcessorType());
            existingCpu.setProcessorSpeed(newCpu.getProcessorSpeed());
            existingCpu.setProcessorUsage(newCpu.getProcessorUsage());
            existingCpu.setCount(newCpu.getCount());
            existingCpu.setUpTime(newCpu.getUpTime());
            return cpuRepository.save(existingCpu);
        });
    }

    //Delete Cpu
    public boolean deleteCpu(Long id) {
        if (cpuRepository.existsById(id)) {
            cpuRepository.deleteById(id);
        } 
       return true;
    }
}
