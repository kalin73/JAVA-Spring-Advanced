package com.softuni.security.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.*;

public class AppUserDetails extends User {
	private static final long serialVersionUID = 5413898698493241479L;
	private String country;
	private String fullName;

	public AppUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public String getCountry() {
		return country;
	}

	public AppUserDetails setCountry(String country) {
		this.country = country;
		return this;
	}

	public String getFullName() {
		return fullName;
	}

	public AppUserDetails setFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}

}
