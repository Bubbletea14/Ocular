package com.github.bubbletea14.ocular.ocular.services.MetricsCollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(MetricCollector.class);
    private final ProcessesRepository processesRepository;
    SystemInfo systemInfo = new SystemInfo();
    OperatingSystem os = systemInfo.getOperatingSystem();
    GlobalMemory globalMemory = systemInfo.getHardware().getMemory();

    @Autowired
    public ProcessesCollector(ProcessesRepository processesRepository) {
        this.processesRepository = processesRepository;
    }

    public void collectAndSaveMetrics() {
        float totalMem = getTotalMemoryInBytes();
        List<OSProcess> processes = getOsProcesses();
        try {
            for (OSProcess process : processes) {
            double cpuPercent = process.getProcessCpuLoadBetweenTicks(process);
            double memPercent = (double) (process.getResidentSetSize() / totalMem) * 100;
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
        }
        } catch (Exception e) {
            logger.error("Failed to collect processes data.", e);
        }
    }

    private List<OSProcess> getOsProcesses() {
        try {
            return os.getProcesses(ProcessFiltering.VALID_PROCESS, ProcessSorting.RSS_DESC, 50);
        } catch (Exception e) {
            logger.error("Error retrieving list of processes.", e);
        } 
        return null;
    }

    private float getTotalMemoryInBytes() {
        try {
            return (float) globalMemory.getTotal(); // (1024 * 1024 * 1024); //to get GB
        } catch (Exception e) {
            logger.error("Error retrieving total memory.", e);
        }
        return -1f;     
    }

    @Scheduled(fixedDelay = 2 * MetricCollector.POLL_SPEED * 1000) //Every 2 Cycles * Seconds each cycle was collected * 1000.
    public void cleanupOldProcesses() {
        LocalDateTime thresholdTime = LocalDateTime.now().minusSeconds(2 * MetricCollector.POLL_SPEED);
        List<Processes> oldProcesses = processesRepository.findByDateTimeBefore(thresholdTime);
        processesRepository.deleteAll(oldProcesses);
        logger.info("Process Table cleanup complete.");
    }
}