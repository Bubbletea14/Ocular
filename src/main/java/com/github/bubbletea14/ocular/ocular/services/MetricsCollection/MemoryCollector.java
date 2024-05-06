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
import oshi.hardware.PhysicalMemory;

import com.github.bubbletea14.ocular.ocular.tables.*;

@Component
public class MemoryCollector {
    private static final int CLEANUP_CYCLE = 200;
    private static final Logger logger = LoggerFactory.getLogger(MetricCollector.class);
    private final MemoryRepository memoryRepository;
    SystemInfo systemInfo = new SystemInfo();
    GlobalMemory globalMemory = systemInfo.getHardware().getMemory();

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
        try {
            float memUsage = getUsedMemory()/getTotalMemory(); 
        return memUsage * 100;
        } catch (Exception e) {
            logger.error("Error retrieving system memory usage.", e);
        }
        return -1;
    }

    private float getTotalMemory() {
        try {
            return (float) globalMemory.getTotal() / (1024 * 1024 * 1024);
        } catch (Exception e) {
            logger.error("Error retrieving total system memory.", e);
        }
        return -1;        
    }

    private float getAvailableMemory() {
        try {
            return (float) globalMemory.getAvailable() / (1024 * 1024 * 1024);
        } catch (Exception e) {
            logger.error("Error retrieving available memory.", e);
        }
        return -1; 
    }

    private float getUsedMemory() {
        try {
            long usedMemoryBytes = globalMemory.getTotal() - globalMemory.getAvailable();
            return (float) usedMemoryBytes / (1024 * 1024 * 1024);
        } catch (Exception e) {
            logger.error("Error retrieving used memory.", e);
        }
        return -1;
    }

    private float getMemorySpeed() {
        try {
            for (PhysicalMemory memory : globalMemory.getPhysicalMemory()) {
                long speed = memory.getClockSpeed();
                return (float) speed / 1_000_000; // Convert to megahertz and return as float
            }
        } catch (Exception e) {
            logger.error("Error retrieving memory speed.", e);
        }
        return -1;
    }

    @Scheduled(fixedDelay = 30 * MetricCollector.POLL_SPEED * 1000) //Every 30 Cycles * Poll speed in seconds we clean older data based on the CLEANUP_CYCLE constant so we can keep a certain amount of data but not wait for it to double to clean it.
    public void cleanupOldCpu() {
        LocalDateTime thresholdTime = LocalDateTime.now().minusSeconds(CLEANUP_CYCLE * MetricCollector.POLL_SPEED);
        List<Memory> oldMemories = memoryRepository.findByDateTimeBefore(thresholdTime);
        memoryRepository.deleteAll(oldMemories);
        logger.info("Memory Table cleanup complete.");
    }
}
