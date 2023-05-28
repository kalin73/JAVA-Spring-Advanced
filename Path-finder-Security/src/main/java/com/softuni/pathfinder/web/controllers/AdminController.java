package com.softuni.pathfinder.web.controllers;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softuni.pathfinder.domain.dtos.binding.RoleChangeForm;
import com.softuni.pathfinder.domain.enums.RoleName;
import com.softuni.pathfinder.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private final UserService userService;

	public AdminController(UserService userService) {
		this.userService = userService;
	}

	@PatchMapping("/changeUserPermission/{id}")
	@ResponseBody
	public Set<RoleName> changeRoles(@PathVariable Long id, @RequestBody RoleChangeForm roleName,
			@RequestParam(defaultValue = "false") Boolean shouldReplaceCurrentRoles) {

		return this.userService.changeUserPermissions(id, roleName, shouldReplaceCurrentRoles);
	}
}
