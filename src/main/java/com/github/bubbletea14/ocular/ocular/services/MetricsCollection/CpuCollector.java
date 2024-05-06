package com.github.bubbletea14.ocular.ocular.services.MetricsCollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import oshi.hardware.CentralProcessor;
import oshi.SystemInfo;
import oshi.software.os.windows.WindowsOperatingSystem;
import oshi.util.FormatUtil;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.time.Instant;
import java.time.LocalDateTime;

import com.github.bubbletea14.ocular.ocular.tables.*;



@Component
public class CpuCollector {
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
            logger.info(e.getMessage());
            logger.info("Error retrieving cpu usage.");
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
            logger.info("Error retrieving cpu speed.");
        }
        return -1;
    }

    public int getProcessorCount() {
        try {
            if (processor != null) {
                return processor.getPhysicalProcessorCount();
            }
        } catch (Exception e) {
            logger.info("Error retrieving cpu count.");
        }
        return -1;
    }

    public String getProcessorModel() {       
        try {
            if (processor != null) {
                return processor.getProcessorIdentifier().getName();
            }
        } catch (Exception e) {
            logger.info("Error retrieving cpu model.");
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
            logger.info("Error retrieving cpu uptime.");
        }
        return -1L;
    }

    // private String getUptimeInDays(Long uptimeInSeconds) {
    //     return FormatUtil.formatElapsedSecs(uptimeInSeconds);
    // }
}
