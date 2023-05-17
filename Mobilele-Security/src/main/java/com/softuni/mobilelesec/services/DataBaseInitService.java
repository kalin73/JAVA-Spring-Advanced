package com.softuni.mobilelesec.services;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class DataBaseInitService {
	private final UserRoleService userRoleService;

	public DataBaseInitService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public void dbInit() {
		if (isDbInit()) {
			this.userRoleService.dbInit();
		}

	}

	@PostConstruct
	public void postConstruct() {
		dbInit();
	}

	public boolean isDbInit() {
		return true;
	}

}
