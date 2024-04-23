package com.github.bubbletea14.ocular.ocular.services.MetricsCollection;

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

    private final CPURepository cpuRepository;

    @Autowired
    public CpuCollector(CPURepository cpuRepository) {
        this.cpuRepository = cpuRepository;
    }

    public void collectAndSaveMetrics() {
        Cpu cpuMetrics = new Cpu();
        cpuMetrics.setDateTime(LocalDateTime.now());
        cpuMetrics.setProcessorType(getProcessorModel());
        cpuMetrics.setProcessorSpeed(getProcessorSpeed());
        cpuMetrics.setCount(getProcessorCount());
        cpuMetrics.setProcessorUptime(getCpuUptime());

        cpuRepository.save(cpuMetrics);
    }

    private double getSystemCpuUsage() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        return osBean.getCpuLoad()* 100;
    }

    public static double getProcessorSpeed() {
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        if (processor != null) {
            double speedMHz = processor.getProcessorIdentifier().getVendorFreq();
            double speedGHz = speedMHz / 1e9;
            return Math.round(speedGHz * 10.0) / 10.0;
        }
        return -1;
    }

    public static int getProcessorCount() {
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        if (processor != null) {
            return processor.getPhysicalProcessorCount();
        }
        return -1;
    }

    public static String getProcessorModel() {
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        if (processor != null) {
            return processor.getProcessorIdentifier().getName();
        }
        return null;
    }

    public static Long getCpuUptime() {
        SystemInfo systemInfo = new SystemInfo();
        WindowsOperatingSystem os = (WindowsOperatingSystem) systemInfo.getOperatingSystem();
        long bootTimeInSeconds = os.getSystemBootTime();
        long currentTimeInSeconds = Instant.now().getEpochSecond();
        return currentTimeInSeconds - bootTimeInSeconds;
    }

    private static String getUptimeInDays(Long uptimeInSeconds) {
        return FormatUtil.formatElapsedSecs(uptimeInSeconds);
    }
}
