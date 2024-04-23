package com.github.bubbletea14.ocular.ocular.services.MetricsCollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.PhysicalMemory;

import com.github.bubbletea14.ocular.ocular.tables.*;

@Component
public class MemoryCollector {

    private final MemoryRepository memoryRepository;

    @Autowired
    public MemoryCollector(MemoryRepository memoryRepository) {
        this.memoryRepository = memoryRepository;
    }

    public void collectAndSaveMetrics() {
        Memory memoryMetrics = new Memory();
        memoryMetrics.setDateTime(LocalDateTime.now());
        memoryMetrics.setTotalMemory(((float)getTotalMemory()));
        memoryMetrics.setFreeMemory(getAvailableMemory());
        memoryMetrics.setUsedMemory(getUsedMemory());
        memoryMetrics.setMemorySpeed(getMemorySpeed());
        memoryMetrics.setMemoryUsagePercentage(getSystemMemoryUsage());

        memoryRepository.save(memoryMetrics);
    }

    private float getSystemMemoryUsage() {
        float memUsage = getUsedMemory()/getTotalMemory(); 
        return memUsage * 100;
    }

    private float getTotalMemory() {
        SystemInfo systemInfo = new SystemInfo();
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();
        return (float) globalMemory.getTotal() / (1024 * 1024 * 1024);
    }

    private float getAvailableMemory() {
        SystemInfo systemInfo = new SystemInfo();
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();
        return (float) globalMemory.getAvailable() / (1024 * 1024 * 1024);
    }

    private float getUsedMemory() {
        SystemInfo systemInfo = new SystemInfo();
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();
        long usedMemoryBytes = globalMemory.getTotal() - globalMemory.getAvailable();
        return (float) usedMemoryBytes / (1024 * 1024 * 1024);
    }

    private float getMemorySpeed() {
        SystemInfo systemInfo = new SystemInfo();
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();
        for (PhysicalMemory memory : globalMemory.getPhysicalMemory()) {
            long speed = memory.getClockSpeed();
            return (float) speed / 1_000_000; // Convert to megahertz and return as float
        }
        return -1; // Return -1 if memory speed is not available
    }
}
