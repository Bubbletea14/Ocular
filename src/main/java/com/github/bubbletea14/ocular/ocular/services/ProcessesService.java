package com.github.bubbletea14.ocular.ocular.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.bubbletea14.ocular.ocular.tables.Processes;
import com.github.bubbletea14.ocular.ocular.tables.ProcessesRepository;


@Service
public class ProcessesService {

    private final ProcessesRepository processesRepository;

    @Autowired
    public ProcessesService(ProcessesRepository processesRepository){
        this.processesRepository = processesRepository;
    }

    // Get Processes List
    public List<Processes> getProcesses() {
        return processesRepository.findAll();
    }	

    // Get Processes by pId
    public Optional<Processes> getProcessByPid (long pId) {
        return processesRepository.findById(pId);
    }

    // Add new processes
    public Processes addProcess(Processes process) {
        return processesRepository.save(process);
    }	
	
    // Update Processes
    public Optional<Processes> updateProcess(Long pId, Processes newProcess) {
        return processesRepository.findById(pId).map(existingProcesses -> {
            existingProcesses.setName(newProcess.getName());
            existingProcesses.setCpuPercent(newProcess.getCpuPercent());
            existingProcesses.setMemPercent(newProcess.getMemPercent());            
            return processesRepository.save(existingProcesses);
        });

    }

    public boolean deleteProcess(Long pId) {
        if (processesRepository.existsById(pId)) {
            processesRepository.deleteById(pId);
        }   
        return true;
    }
		
}
