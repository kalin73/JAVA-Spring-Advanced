package com.softuni.events.web;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.softuni.events.model.OrderDTO;
import com.softuni.events.service.OrderService;

@Controller
public class OrderController {
	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/orders/dummy/create")
	public void createDummyOrder() {
		OrderDTO orderDTO = new OrderDTO();

		for (int i = 0; i < 3; i++) {
			orderDTO.addProductID(new Random().nextLong(100));
		}
		this.orderService.createOrder(orderDTO);
	}
}
