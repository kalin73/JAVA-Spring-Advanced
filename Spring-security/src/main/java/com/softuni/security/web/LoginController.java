package com.softuni.security.web;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	@GetMapping("/users/login")
	public String login() {
		return "auth-login";
	}

	@PostMapping("/users/login-error")
	public String onFailedLogin(RedirectAttributes redirectAttributes,
			@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username) {
		redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY,
				username);
		redirectAttributes.addFlashAttribute("bad_credentials", true);

		return "redirect:/users/login";
	}
}
