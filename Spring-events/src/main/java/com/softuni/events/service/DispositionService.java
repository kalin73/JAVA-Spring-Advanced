package com.softuni.events.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.softuni.events.model.OrderCreatedEvent;

@Service
public class DispositionService {
private static final Logger LOGGER = LoggerFactory.getLogger(DispositionService.class);
	
	@EventListener
	public void onOrderCreated(OrderCreatedEvent evt) {
		LOGGER.info("Disposition for added products {}", evt.getAllProductIDs());
	}
}
