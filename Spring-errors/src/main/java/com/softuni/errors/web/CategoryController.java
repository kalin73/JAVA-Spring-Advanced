package com.softuni.errors.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.softuni.errors.model.ObjectNotFoundException;

@Controller
public class CategoryController {
	@GetMapping("/categories/{id}")
	public String getCategoryById(@PathVariable("id") Long productId) {
		throw new ObjectNotFoundException(productId, "Category");
	}
}
