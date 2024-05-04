package com.softuni.mobilelesec.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softuni.mobilelesec.domain.dtos.binding.UserRegisterFormDto;
import com.softuni.mobilelesec.domain.dtos.view.UserRoleViewDto;
import com.softuni.mobilelesec.services.UserRoleService;
import com.softuni.mobilelesec.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class RegistrationController {
	private static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult.";
	private final UserRoleService userRoleService;
	private final UserService userService;

	public RegistrationController(UserRoleService userRoleService, UserService userService) {
		this.userRoleService = userRoleService;
		this.userService = userService;
	}

	@GetMapping("/register")
	public String getRegister() {
		return "auth-register";
	}

	@PostMapping("/register")
	public String postRegister(@Valid UserRegisterFormDto userRegisterForm,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("userRegisterForm", userRegisterForm)
					.addFlashAttribute(BINDING_RESULT_PATH + "userRegisterForm", bindingResult);

			return "redirect:/users/register";
		}

		this.userService.registerUser(userRegisterForm);

		return "redirect:/users/login";
	}

	@ModelAttribute(name = "userRegisterForm")
	public UserRegisterFormDto initUserRegisterFormDto() {
		return new UserRegisterFormDto();
	}

	@ModelAttribute(name = "roles")
	public List<UserRoleViewDto> getAllRoles() {
		return this.userRoleService.getAll();

	}
}
