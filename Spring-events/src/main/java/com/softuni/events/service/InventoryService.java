package com.softuni.events.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.softuni.events.model.OrderCreatedEvent;

@Service
public class InventoryService {
private static final Logger LOGGER = LoggerFactory.getLogger(InventoryService.class);
	
	@EventListener
	public void onOrderCreated(OrderCreatedEvent evt) {
		LOGGER.info("Inventory subtracted for added products {}", evt.getAllProductIDs());
	}
}
