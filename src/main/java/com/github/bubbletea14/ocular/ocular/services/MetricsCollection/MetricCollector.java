package com.github.bubbletea14.ocular.ocular.services.MetricsCollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class MetricCollector {
    public static final int POLL_SPEED = 5;
    private static final Logger logger = LoggerFactory.getLogger(MetricCollector.class);
    private final CpuCollector cpuCollector;
    private final MemoryCollector memoryCollector;
    private final ProcessesCollector processesCollector;
    private Timer timer;

    @Autowired
    public MetricCollector(CpuCollector cpuCollector, MemoryCollector memoryCollector, ProcessesCollector processesCollector) {
        this.cpuCollector = cpuCollector;
        this.memoryCollector = memoryCollector;
        this.processesCollector = processesCollector;
    }

    public void startCollectingMetrics() {
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                collectAndSaveMetrics();
            }
        }, 0, POLL_SPEED * 1000);
    }

    private void collectAndSaveMetrics() {
        logger.info("New Cycle");
        cpuCollector.collectAndSaveMetrics();
        memoryCollector.collectAndSaveMetrics();
        processesCollector.collectAndSaveMetrics();
    }

    public void stopCollectingMetrics() {
        if (timer != null) {
            timer.cancel();
        }
        logger.info("Metrics collection stopped.");
        logger.info("Closing Ocular.");
    }
}
