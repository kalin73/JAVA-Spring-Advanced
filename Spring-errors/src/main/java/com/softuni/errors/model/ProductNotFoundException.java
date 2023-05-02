package com.softuni.errors.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product was not found")
public class ProductNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Long productId;

	public ProductNotFoundException(Long productId) {
		super("Product with ID " + productId + " not found!");
		this.productId = productId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
