package com.github.bubbletea14.ocular.ocular.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.bubbletea14.ocular.ocular.tables.Processes;


@Service
public class ProcessesService {
    
    // public List<Processes> getProcesses(){
    //     return List.of(new Processes(1L, "Browser"));
    // }

    private final List<Processes> processesList = new ArrayList<>();

    public ProcessesService() {
        processesList.add(new Processes(1L, "Chrome"));
        processesList.add(new Processes(2L, "Discord"));
        processesList.add(new Processes(3L, "Spotify"));
    }

    public Processes getProcessByPid (long pId) {
        for (Processes process: processesList) {
            if (process.getPId() == pId) {
                return process;
            }
        }
        return null;
    }

    public List<Processes> getProcesses() {
			return processesList;
	}	
    
    public void addProcess(Processes process) {
        processesList.add(process);
    }	
	
    public void updateProcess(Long pId, Processes newProcess) {
        for (Processes process : processesList) {
            if (process.getPId().equals(pId)) {
                process.setName(newProcess.getName());
                break;
            }
        }
    }

    public void deleteProcess(Long pId) {
        processesList.removeIf(process -> process.getPId().equals(pId));
    }
		
}
