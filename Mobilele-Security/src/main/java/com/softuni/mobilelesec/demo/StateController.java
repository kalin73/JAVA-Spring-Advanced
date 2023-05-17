package com.softuni.mobilelesec.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.softuni.mobilelesec.domain.dtos.binding.UserRegisterFormDto;
import com.softuni.mobilelesec.web.BaseController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/demo")
public class StateController extends BaseController {
	private static final String STATE_USERNAME_KEY = "username";

	@GetMapping("/register")
	public ModelAndView getRegister() {
		return super.view("demo/register");
	}

	@PostMapping("/loginTestCookies")
	public ModelAndView postRegister(HttpServletResponse response,
			@RequestParam(name = STATE_USERNAME_KEY) String username) {

		Cookie cookie = new Cookie(STATE_USERNAME_KEY, username);
		response.addCookie(cookie);

		return super.redirect("/demo/login");
	}

	@PostMapping("/register")
	public ModelAndView postRegister(UserRegisterFormDto userRegisterFormDto, HttpSession httpSession) {
		httpSession.setAttribute(STATE_USERNAME_KEY, userRegisterFormDto.getUsername());

		return super.redirect("/demo/login");
	}

	@GetMapping("/loginTestCookies")
	public ModelAndView getLogin(ModelAndView modelAndView,
			@CookieValue(name = STATE_USERNAME_KEY, defaultValue = "") String username) {

		modelAndView.addObject(STATE_USERNAME_KEY, username);
		
		return super.view("demo/login", modelAndView);
	}

	@GetMapping("/login")
	public ModelAndView getLogin2(ModelAndView modelAndView, HttpSession httpSession) {
		String attribute = httpSession.getAttribute(STATE_USERNAME_KEY).toString();
		modelAndView.addObject(STATE_USERNAME_KEY, attribute);

		return super.view("demo/login", modelAndView);
	}
}
