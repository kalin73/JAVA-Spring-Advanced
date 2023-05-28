package com.softuni.pathfinder.web.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.softuni.pathfinder.domain.dtos.view.UserProfileModel;
import com.softuni.pathfinder.domain.entities.UserEntity;
import com.softuni.pathfinder.repositories.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/profile")
	public ModelAndView getProfile(ModelAndView modelAndView, Principal principal) {
		UserEntity user = userRepository.findByUsername(principal.getName()).get();
		UserProfileModel userProfileModel = new UserProfileModel();
		
		userProfileModel.setFullName(user.getFullName())
			.setAge(user.getAge())
			.setLevel(user.getLevel())
			.setUsername(user.getUsername());
		
		
		return super.view("profile", modelAndView.addObject("profile", userProfileModel));
	}
}
