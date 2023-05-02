package com.softuni.errors.service;

import org.springframework.stereotype.Service;

import com.softuni.errors.model.ProductDTO;
import com.softuni.errors.model.ProductNotFoundException;

@Service
public class ProductService {
	public ProductDTO getProductByID(long productId) {
		throw new ProductNotFoundException(productId);
	}
}
