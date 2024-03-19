package com.mscs.project.ocular.ocular;

import com.mscs.project.ocular.ocular.Services.MetricsCollection.MetricCollector;

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

	public static void main(String[] args) {
		System.out.println("Start Main");
		SpringApplication.run(OcularApplication.class, args);
		System.out.println("Main Finish");
	}

	@PostConstruct
	public void startMetricCollection() {
		System.out.println("StartMC");
		metricCollector.startCollectingMetrics();
		System.out.println("FinishMC");
	}
}
