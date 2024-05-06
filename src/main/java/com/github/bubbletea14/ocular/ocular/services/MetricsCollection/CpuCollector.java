package com.github.bubbletea14.ocular.ocular.services.MetricsCollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import oshi.hardware.CentralProcessor;
import oshi.SystemInfo;
import oshi.software.os.windows.WindowsOperatingSystem;
import oshi.util.FormatUtil;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import com.github.bubbletea14.ocular.ocular.tables.*;



@Component
public class CpuCollector {
    private static final int CLEANUP_CYCLE = 200;
    private static final Logger logger = LoggerFactory.getLogger(MetricCollector.class);
    private final CPURepository cpuRepository;
    private SystemInfo systemInfo = new SystemInfo();
    private CentralProcessor processor = systemInfo.getHardware().getProcessor();
    private long[] previousTicks;

    @Autowired
    public CpuCollector(CPURepository cpuRepository) {
        this.cpuRepository = cpuRepository;
        previousTicks = processor.getSystemCpuLoadTicks();
    }

    public void collectAndSaveMetrics() {
        Cpu cpuMetrics = new Cpu();
        cpuMetrics.setDateTime(LocalDateTime.now());
        cpuMetrics.setProcessorType(getProcessorModel());
        cpuMetrics.setProcessorSpeed(getProcessorSpeed());
        cpuMetrics.setCount(getProcessorCount());
        cpuMetrics.setProcessorUptime(getCpuUptime());
        cpuMetrics.setProcessorUsage(getSystemCpuUsage());
        
        cpuRepository.save(cpuMetrics);
    }

    private double getSystemCpuUsage() {
        try {
            double load = processor.getSystemCpuLoadBetweenTicks(previousTicks);
            previousTicks = processor.getSystemCpuLoadTicks();
            return load * 100;
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("Error retrieving cpu usage.");
        }
        return -1;
    }

    public double getProcessorSpeed() {
        try {
            if (processor != null) {
                double speedMHz = processor.getProcessorIdentifier().getVendorFreq();
                double speedGHz = speedMHz / 1e9;
                return Math.round(speedGHz * 10.0) / 10.0;
            }
        } catch (Exception e) {
            logger.error("Error retrieving cpu speed.");
        }
        return -1;
    }

    public int getProcessorCount() {
        try {
            if (processor != null) {
                return processor.getPhysicalProcessorCount();
            }
        } catch (Exception e) {
            logger.error("Error retrieving cpu count.");
        }
        return -1;
    }

    public String getProcessorModel() {       
        try {
            if (processor != null) {
                return processor.getProcessorIdentifier().getName();
            }
        } catch (Exception e) {
            logger.error("Error retrieving cpu model.");
        }
        return null;
    }

    public Long getCpuUptime() {
        try {
            SystemInfo systemInfo = new SystemInfo();
            WindowsOperatingSystem os = (WindowsOperatingSystem) systemInfo.getOperatingSystem();
            long bootTimeInSeconds = os.getSystemBootTime();
            long currentTimeInSeconds = Instant.now().getEpochSecond();
            return currentTimeInSeconds - bootTimeInSeconds;
        } catch (Exception e) {
            logger.error("Error retrieving cpu uptime.");
        }
        return -1L;
    }

    private String getUptimeInDays(Long uptimeInSeconds) {
        return FormatUtil.formatElapsedSecs(uptimeInSeconds);
    }

    @Scheduled(fixedDelay = 30 * MetricCollector.POLL_SPEED * 1000) //Every 20 Cycles * Seconds each cycle was collected * 1000.
    public void cleanupOldCpu() {
        LocalDateTime thresholdTime = LocalDateTime.now().minusSeconds(CLEANUP_CYCLE * MetricCollector.POLL_SPEED);
        List<Cpu> oldCpu = cpuRepository.findByDateTimeBefore(thresholdTime);
        cpuRepository.deleteAll(oldCpu);
        logger.info("Cpu Table cleanup complete.");
    }
}
