package com.softuni.errors.model;

public class ProductErrorDTO {
	private long productId;
	private String description;

	public ProductErrorDTO(long productId, String description) {
		this.productId = productId;
		this.description = description;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
