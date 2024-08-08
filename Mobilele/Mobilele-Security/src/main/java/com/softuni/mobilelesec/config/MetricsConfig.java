package com.softuni.mobilelesec.config;

import org.springframework.context.annotation.Configuration;

import com.softuni.mobilelesec.repositories.OfferRepository;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class MetricsConfig {

	public MetricsConfig(MeterRegistry meterRegistry, OfferRepository offerRepository) {
		Gauge.builder("offers.count", offerRepository::count).register(meterRegistry);
	}

}
