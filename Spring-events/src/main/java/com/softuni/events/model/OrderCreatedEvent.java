package com.softuni.events.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationEvent;

public class OrderCreatedEvent extends ApplicationEvent{
	private static final long serialVersionUID = 1L;
	private List<Long> allProductIDs = new ArrayList<>();

	public OrderCreatedEvent(Object source) {
		super(source);
	}


	public List<Long> getAllProductIDs() {
		return allProductIDs;
	}

	public OrderCreatedEvent setAllProductIDs(List<Long> allProductIDs) {
		this.allProductIDs = allProductIDs;
		return this;
	}

	public void addProductID(Long productID) {
		this.allProductIDs.add(productID);
	}

}
