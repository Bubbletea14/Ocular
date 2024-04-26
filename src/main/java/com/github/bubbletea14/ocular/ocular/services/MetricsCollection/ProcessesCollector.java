package com.github.bubbletea14.ocular.ocular.services.MetricsCollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystem.ProcessFiltering;
import oshi.software.os.OperatingSystem.ProcessSorting;

import com.github.bubbletea14.ocular.ocular.tables.*;

@Component
public class ProcessesCollector {

    private final ProcessesRepository processesRepository;

    @Autowired
    public ProcessesCollector(ProcessesRepository processesRepository) {
        this.processesRepository = processesRepository;
    }

    public void collectAndSaveMetrics() {
        float totalMem = getTotalMemoryInBytes();
        List<OSProcess> processes = getOsProcesses();
        //System.out.format("%-25s%-25s%-25s%-25s%-25s%n", "Name:","PID:","PPId:", "CPU %:", "Mem %:");
        for (OSProcess process : processes) {
            double cpuPercent = process.getProcessCpuLoadBetweenTicks(process);
            double memPercent = (double) process.getResidentSetSize() / totalMem * 100;
            cpuPercent = Math.round(cpuPercent * 10.0) / 10.0;
            memPercent = Math.round(memPercent * 10.0) / 10.0;

            Processes processesMetrics = new Processes();
            processesMetrics.setDateTime(LocalDateTime.now());
            processesMetrics.setName(process.getName());
            processesMetrics.setPId(process.getProcessID());
            processesMetrics.setParentPId(process.getParentProcessID());
            processesMetrics.setCpuPercent(cpuPercent);
            processesMetrics.setMemPercent(memPercent);

            processesRepository.save(processesMetrics);
            
            //System.out.format("%-25s%-25s%-25s%-25s%-25s%n", processesMetrics.getName(),processesMetrics.getPId(),processesMetrics.getParentPId(),processesMetrics.getCpuPercent(),processesMetrics.getMemPercent());   
        }
        System.out.println("");
    }

    private List<OSProcess> getOsProcesses() {
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem os = systemInfo.getOperatingSystem();
        return os.getProcesses(ProcessFiltering.VALID_PROCESS, ProcessSorting.RSS_DESC, 30);
    }

    private float getTotalMemoryInBytes() {
        SystemInfo systemInfo = new SystemInfo();
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();
        return (float) globalMemory.getTotal() / (1024 * 1024 * 1024); //to get GB
    }

    //@Scheduled(fixedDelay = 20 * MetricCollector.POLL_SPEED * 1000) //Every 10 Cycles * Seconds each cycle was collected * 1000.
    public void cleanupOldProcesses() {
        System.out.println("Cleanup Started.***************************************************************************************");
        LocalDateTime thresholdTime = LocalDateTime.now().minusSeconds(20 * MetricCollector.POLL_SPEED);
        List<Processes> oldProcesses = processesRepository.findByDateTimeBefore(thresholdTime);
        for (Processes process : oldProcesses) {
            System.out.println("Name: " + process.getName() + ", PID: " + process.getPId() + ", CPU %: " + process.getCpuPercent() + ", Memory %: " + process.getMemPercent());
        }
        //processesRepository.deleteAll(oldProcesses);
        Processes newProcess = processesRepository.findFirstByOrderByDateTimeDesc();
        System.out.println("DateTime: " + newProcess.getDateTime() + ", Name: " + newProcess.getName());
        System.out.println("Cleanup completed.***********************************************************************************");
    }
}