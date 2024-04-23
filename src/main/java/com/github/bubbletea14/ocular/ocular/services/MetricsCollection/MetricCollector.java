package com.github.bubbletea14.ocular.ocular.services.MetricsCollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class MetricCollector {
    private static final int POLL_SPEED = 15;

    private final CpuCollector cpuCollector;
    private final MemoryCollector memoryCollector;
    private final ProcessesCollector processesCollector;

    @Autowired
    public MetricCollector(CpuCollector cpuCollector, MemoryCollector memoryCollector, ProcessesCollector processesCollector) {
        this.cpuCollector = cpuCollector;
        this.memoryCollector = memoryCollector;
        this.processesCollector = processesCollector;
    }

    public void startCollectingMetrics() {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                collectAndSaveMetrics();
            }
        }, 0, POLL_SPEED * 1000);
    }

    private void collectAndSaveMetrics() {
        System.out.println("New Cycle");
        cpuCollector.collectAndSaveMetrics();
        memoryCollector.collectAndSaveMetrics();
        processesCollector.collectAndSaveMetrics();
    }
}
