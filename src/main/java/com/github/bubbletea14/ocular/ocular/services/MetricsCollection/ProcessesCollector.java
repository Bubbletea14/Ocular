package com.github.bubbletea14.ocular.ocular.services.MetricsCollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import com.sun.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import oshi.SystemInfo;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase;
import com.github.bubbletea14.ocular.ocular.tables.*;

@Component
public class ProcessesCollector {

    private final ProcessesRepository processesRepository;

    @Autowired
    public ProcessesCollector(ProcessesRepository processesRepository) {
        this.processesRepository = processesRepository;
    }

    public void collectAndSaveMetrics() {
        String processName = "ExampleProcess";  // Replace with actual process name logic
        Long pId = 123L;  // Replace with actual process ID logic
        int cpuPercent = 5;
        int memPercent = 5;
        
        Processes processesMetrics = new Processes();
        processesMetrics.setDateTime(LocalDateTime.now());
        processesMetrics.setName(processName);
        processesMetrics.setPId(pId);
        processesMetrics.setCpuPercent(cpuPercent);
        processesMetrics.setMemPercent(memPercent);

        processesRepository.save(processesMetrics);
        System.out.println(processName);
    }

    private List<OSProcess> getOsProcesses() {
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem os = systemInfo.getOperatingSystem();
        List<OSProcess> processes = os.getProcesses(null, null, 0);
        return processes;
    }
}