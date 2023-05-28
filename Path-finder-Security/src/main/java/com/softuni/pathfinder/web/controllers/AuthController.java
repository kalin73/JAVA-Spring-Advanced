package com.softuni.pathfinder.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softuni.pathfinder.domain.dtos.binding.UserLoginForm;
import com.softuni.pathfinder.domain.dtos.binding.UserRegisterForm;
import com.softuni.pathfinder.services.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {
	private final UserService userService;

	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/register")
	public ModelAndView getRegister() {
		return super.view("register");
	}

	@PostMapping("/register")
	public ModelAndView postRegister(ModelAndView modelAndView, @Validated UserRegisterForm userRegisterForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return super.view("register", modelAndView.addObject(userRegisterForm));
		}

		this.userService.registerUser(userRegisterForm);

		return super.redirect("login");
	}

	@GetMapping("/login")
	public ModelAndView getLogin() {
		return super.view("login");
	}

	@PostMapping("/login-error")
	public String onLoginError(@ModelAttribute(name = "username") String username,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("username", username);
		redirectAttributes.addFlashAttribute("badCredentials", true);

		return "redirect:/auth/login";
	}

//	@PostMapping("/login")
//	public ModelAndView postLogin(@Valid UserLoginForm userLoginForm, BindingResult bindingResult,
//			ModelAndView modelAndView) {
//		if (bindingResult.hasErrors()) {
//			return super.view("login", modelAndView.addObject(userLoginForm));
//		}
//
//		return this.userService.loginUser(userLoginForm).isValid() ? super.redirect("/") : super.redirect("login");
//	}

//	@GetMapping("/logout")
//	public ModelAndView logout() {
//		this.userService.logout();
//
//		return super.redirect("/");
//	}

//	@PostMapping("/login-error")
//	public String onFailedLogin(RedirectAttributes redirectAttributes,
//			@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username) {
//		redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY,
//				username);
//		redirectAttributes.addFlashAttribute("bad_credentials", true);
//
//		return "redirect:/auth/login";
//	}

	@ModelAttribute("userRegisterForm")
	public UserRegisterForm initRegisterForm() {
		return new UserRegisterForm();
	}

	@ModelAttribute("userLoginForm")
	public UserLoginForm initLoginForm() {
		return new UserLoginForm();
	}
}