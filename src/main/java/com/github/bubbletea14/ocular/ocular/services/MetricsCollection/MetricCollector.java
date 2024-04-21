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

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase;
import com.github.bubbletea14.ocular.ocular.tables.*;



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
        float memoryUsage = getSystemMemoryUsage();
        System.out.println("Mem usage = "+memoryUsage);
        System.out.println("Total Memory = "+ getTotalMemory());
        System.out.println("Total Physical Memory = "+ getTotalPhysicalMemory());
        String processName = "ExampleProcess";  // Replace with actual process name logic
        Long pId = 123L;  // Replace with actual process ID logic
        int cpuPercent = getProcessCpuUsage(pId);
        int memPercent = getProcessMemoryUsage(pId);

        LocalDateTime timestamp = LocalDateTime.now();
        System.out.println("Current Time = "+timestamp.toString());

        // Save CPU metrics
        Cpu cpuMetrics = new Cpu();
        cpuMetrics.setDateTime(LocalDateTime.now());
        System.out.println(getProcessorName());

        cpuRepository.save(cpuMetrics);
        // Set other CPU metrics...        

        // Save Memory metrics
        Memory memoryMetrics = new Memory();
        memoryMetrics.setDateTime(LocalDateTime.now());
        memoryMetrics.setMemoryUsagePercentage(memoryUsage);
        // Set other Memory metrics...

        memoryRepository.save(memoryMetrics);

        // Save Processes metrics
        Processes processesMetrics = new Processes();
        processesMetrics.setDateTime(timestamp);
        processesMetrics.setName(processName);
        processesMetrics.setPId(pId);
        processesMetrics.setCpuPercent(cpuPercent);
        processesMetrics.setMemPercent(memPercent);

        processesRepository.save(processesMetrics);
        System.out.println(timestamp.toString());
    }

    private double getSystemCpuUsage() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        return osBean.getCpuLoad()* 100;
    }

    private String getProcessorName() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        return osBean.getArch() + " " + osBean.getAvailableProcessors() + " processors";
    }

    private float getSystemMemoryUsage() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        return ((float) (memoryMXBean.getHeapMemoryUsage().getUsed() + memoryMXBean.getNonHeapMemoryUsage().getUsed())
                / (float) (memoryMXBean.getHeapMemoryUsage().getMax() + memoryMXBean.getNonHeapMemoryUsage().getMax())) * 100.0f;
    }

    private double getTotalMemory() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        return (double) (memoryMXBean.getHeapMemoryUsage().getMax() + memoryMXBean.getNonHeapMemoryUsage().getMax()) / (1024 * 1024 * 1024);
    }

    private static long getTotalPhysicalMemory() {
        Kernel32 kernel32 = Kernel32.INSTANCE;
        Kernel32.MEMORYSTATUSEX memoryStatus = new Kernel32.MEMORYSTATUSEX();
        if (kernel32.GlobalMemoryStatusEx(memoryStatus)) {
            return memoryStatus.ullTotalPhys.longValue() / (1024 * 1024 * 1024);
        }
        return -1;
    }

    public static long getMemorySpeed() {
        return 1L;
    }

    private int getProcessCpuUsage(Long processId) {
        return 0;
    }

    private int getProcessMemoryUsage(Long processId) {
        return 0;
    }
}
