package com.softuni.errors.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.softuni.errors.model.ProductDTO;
import com.softuni.errors.model.ProductNotFoundException;
import com.softuni.errors.service.ProductService;

@Controller
public class ProductController {
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products/{id}")
	public String getProductById(@PathVariable("id") Long productId, Model model) {
		ProductDTO productDTO = productService.getProductByID(productId);
		model.addAttribute("product", productDTO);

		return "product-details";
	}

	@GetMapping("/products")
	public String getProducts() {
		throw new NullPointerException("OOOPS bug.");
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView onProductNotFound(ProductNotFoundException pnfe) {
		ModelAndView modelAndView = new ModelAndView("product-not-found");

		modelAndView.addObject("productId", pnfe.getProductId());

		return modelAndView;
	}

}
