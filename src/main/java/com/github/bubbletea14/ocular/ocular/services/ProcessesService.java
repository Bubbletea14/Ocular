package com.github.bubbletea14.ocular.ocular.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.github.bubbletea14.ocular.ocular.tables.Processes;


@Service
public class ProcessesService {

    private final List<Processes> processesList = new ArrayList<>();

    public ProcessesService() {
        processesList.add(new Processes(1, "Chrome"));
        processesList.add(new Processes(2, "Discord"));
    }

    public Processes getProcessByPid (long pId) {
        for (Processes process: processesList) {
            if (process.getPId() == pId) {
                return process;
            }
        }
        throw new NoSuchElementException("Processes with PID " + pId + " not found"); 
    }

    public List<Processes> getProcesses() {
			return processesList;
	}	
    
    public Processes addProcess(Processes process) {
        processesList.add(process);
        return process;
    }	
	
    public Processes updateProcess(Long pId, Processes newProcess) {
        for (Processes process : processesList) {
            if (process.getPId()== pId) {
                process.setName(newProcess.getName());
                return process;
            }
        }
        return null; //If no process with the given PID founded 
    }

    public boolean deleteProcess(Long pId) {
        return processesList.removeIf(process -> process.getPId() == (pId));
    }
		
}
