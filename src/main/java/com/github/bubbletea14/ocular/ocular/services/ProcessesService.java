package com.github.bubbletea14.ocular.ocular.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.bubbletea14.ocular.ocular.tables.Processes;

@Service
public class ProcessesService {
    
    public List<Processes> getProcesses(){
        return List.of(new Processes(1L, "Browser"));
    }

}
