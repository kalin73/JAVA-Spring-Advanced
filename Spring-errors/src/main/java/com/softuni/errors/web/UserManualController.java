package com.softuni.errors.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.softuni.errors.model.ObjectNotFoundException;

@Controller
public class UserManualController {
	@GetMapping("/manuals/{id}")
	public String getProducts(@PathVariable("id") Long productId) {
		throw new ObjectNotFoundException(productId, "User manual");
	}
}
