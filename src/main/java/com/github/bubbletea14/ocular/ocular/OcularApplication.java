package com.github.bubbletea14.ocular.ocular;

import com.github.bubbletea14.ocular.ocular.services.MetricsCollection.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class OcularApplication {
	private final MetricCollector metricCollector;

	@Autowired
	public OcularApplication(MetricCollector metricCollector) {
		System.out.println("Create MC");
		this.metricCollector = metricCollector;
		System.out.println("MC Created");
	}

	public static void main(String[] args) 
	{
		SpringApplication.run(OcularApplication.class, args);
		System.out.println("Hello");
	}

	@PostConstruct
	public void startMetricCollection() {
		System.out.println("StartMC");
		metricCollector.startCollectingMetrics();
		System.out.println("FinishMC");
	}
}
