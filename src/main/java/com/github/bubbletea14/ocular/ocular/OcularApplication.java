package com.github.bubbletea14.ocular.ocular;

import com.github.bubbletea14.ocular.ocular.Classes.SystemTrayManager;
import com.github.bubbletea14.ocular.ocular.services.MetricsCollection.*;
import com.github.bubbletea14.ocular.ocular.tables.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableScheduling
public class OcularApplication {
	private final MetricCollector metricCollector;
	private static final Logger logger = LoggerFactory.getLogger(MetricCollector.class);

	@Autowired
	public OcularApplication(MetricCollector metricCollector) {
		this.metricCollector = metricCollector;
	}

	public static void main(String[] args) 
	{
		SpringApplication.run(OcularApplication.class, args);
	}

	@PostConstruct
	public void startMetricCollection() {
		metricCollector.startCollectingMetrics();
		logger.info("Metrics collector started.");
		SystemTrayManager trayManager = new SystemTrayManager(metricCollector);
		trayManager.createSystemTrayIcon();
	}

	@Bean
	public Cpu ReturnCpu() {
		return new Cpu();
	}

	@Bean
	public Memory ReturnMemory() {
		return new Memory();
	}

	@Bean
	public Processes ReturnProcesses() {
		return new Processes();
	}

	@Bean
	public Users ReturnUsers() {
		return new Users();
	}
}
