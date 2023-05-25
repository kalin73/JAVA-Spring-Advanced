package com.softuni.mobilelesec.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class LoginController extends BaseController{
	
	@GetMapping("/login")
	public ModelAndView getLogin() {
		return super.view("auth-login");
	}

	@PostMapping("/login-error")
	public String onFailedLogin(@ModelAttribute(name = "username") String username,
			RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute("username", username);
		redirectAttributes.addFlashAttribute("bad_credentials", true);

		return "redirect:/users/login";
	}
}
