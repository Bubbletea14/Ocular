package com.mscs.project.ocular.ocular.Services.MetricsCollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.mscs.project.ocular.ocular.Tables.*;


@Component
public class MetricCollector {
    private static final int POLL_SPEED = 15;

    private final CPURepository cpuRepository;
    private final MemoryRepository memoryRepository;
    private final ProcessesRepository processesRepository;

    @Autowired
    public MetricCollector(CPURepository cpuRepository, MemoryRepository memoryRepository, ProcessesRepository processesRepository) {
        this.cpuRepository = cpuRepository;
        this.memoryRepository = memoryRepository;
        this.processesRepository = processesRepository;
    }

    public void startCollectingMetrics() {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                collectAndSaveMetrics();
            }
        }, 0, POLL_SPEED * 1000); // Run every 30 seconds
    }

    private void collectAndSaveMetrics() {
        System.out.println("New Cycle");
        // Simulate some metrics, replace with your actual metric collection logic
        double cpuUtilization = getSystemCpuUsage();
        System.out.println("Cpu Util = "+ cpuUtilization);
        double memoryUsage = getSystemMemoryUsage();
        System.out.println("Mem usage = "+memoryUsage);
        String processName = "ExampleProcess";  // Replace with actual process name logic
        int pId = 123;  // Replace with actual process ID logic
        int cpuPercent = getProcessCpuUsage(pId);
        int memPercent = getProcessMemoryUsage(pId);

        LocalDateTime timestamp = LocalDateTime.now();
        System.out.println("Current Time = "+timestamp.toString());

        // Save CPU metrics
        CPU cpuMetrics = new CPU();
        cpuMetrics.setDateTime(LocalDateTime.now());
        cpuMetrics.setCpuUtilization(cpuUtilization);
        System.out.println(getProcessorName());
        // Set other CPU metrics...

        try {
           cpuRepository.save(cpuMetrics); 
        }
        catch(Exception e) {
            int test= 5;
        }
        

        // Save Memory metrics
        Memory memoryMetrics = new Memory();
        memoryMetrics.setDateTime(LocalDateTime.now());
        memoryMetrics.setMemoryUsage(memoryUsage);
        // Set other Memory metrics...

        memoryRepository.save(memoryMetrics);

        // Save Processes metrics
        Processes processesMetrics = new Processes();
        processesMetrics.setDateTime(timestamp);
        processesMetrics.setProcessName(processName);
        processesMetrics.setpId(pId);
        processesMetrics.setCpuPercent(cpuPercent);
        processesMetrics.setMemPercent(memPercent);

        processesRepository.save(processesMetrics);
        System.out.println(timestamp.toString());
    }

    private double getSystemCpuUsage() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        return osBean.getSystemLoadAverage();
    }

    private String getProcessorName() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        return osBean.getArch() + " " + osBean.getAvailableProcessors() + " processors";
    }

    private double getSystemMemoryUsage() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        return (double) (memoryMXBean.getHeapMemoryUsage().getUsed() + memoryMXBean.getNonHeapMemoryUsage().getUsed())
                / (double) (memoryMXBean.getHeapMemoryUsage().getMax() + memoryMXBean.getNonHeapMemoryUsage().getMax()) * 100.0;
    }

    private int getProcessCpuUsage(int processId) {
        return 0;
    }

    private int getProcessMemoryUsage(int processId) {
        return 0;
    }
}
