package com.softuni.events.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.softuni.events.model.OrderCreatedEvent;
import com.softuni.events.model.OrderDTO;

@Service
public class OrderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
	private ApplicationEventPublisher applicationEventPublisher;

	public OrderService(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	public void createOrder(OrderDTO order) {
		LOGGER.info("Order was created!");
		
		OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(this).setAllProductIDs(order.getAllProductIDs());
		
		applicationEventPublisher.publishEvent(orderCreatedEvent);
	}
}
