package com.softuni.errors.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softuni.errors.model.ProductDTO;
import com.softuni.errors.model.ProductErrorDTO;
import com.softuni.errors.model.ProductNotFoundException;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") long id) {
		throw new ProductNotFoundException(id);
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ProductErrorDTO> onProductNotFound(ProductNotFoundException pnfe) {
		ProductErrorDTO productErrorDTO = new ProductErrorDTO(pnfe.getProductId(), "Product not found!");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productErrorDTO);

	}

}
