package com.softuni.events.model;

import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
	private List<Long> allProductIDs = new ArrayList<>();

	public List<Long> getAllProductIDs() {
		return allProductIDs;
	}

	public OrderDTO setAllProductIDs(List<Long> allProductIDs) {
		this.allProductIDs = allProductIDs;
		return this;
	}

	public void addProductID(Long productID) {
		this.allProductIDs.add(productID);
	}
}
