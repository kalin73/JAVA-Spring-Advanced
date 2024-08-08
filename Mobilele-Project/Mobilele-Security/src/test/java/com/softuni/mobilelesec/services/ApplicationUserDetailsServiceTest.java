package com.softuni.mobilelesec.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.softuni.mobilelesec.domain.entities.UserEntity;
import com.softuni.mobilelesec.domain.entities.UserRoleEntity;
import com.softuni.mobilelesec.domain.enums.UserRoleEnum;
import com.softuni.mobilelesec.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class ApplicationUserDetailsServiceTest {
	private ApplicationUserDetailsService toTest;

	private final String EXISTING_EMAIL = "admin@example.com";
	private final String NOT_EXISTING_EMAIL = "pesho@example.com";

	@Mock
	private UserRepository mockUserRepository;

	@BeforeEach
	void setUp() {
		toTest = new ApplicationUserDetailsService(mockUserRepository);
	}

	@Test
	void testUserFound() {
		UserRoleEntity testAdminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
		UserRoleEntity testUserRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

		UserEntity testUserEntity = new UserEntity().setEmail(EXISTING_EMAIL).setPassword("topsecret")
				.setRoles(List.of(testAdminRole, testUserRole));

		when(mockUserRepository.findByEmail(EXISTING_EMAIL)).thenReturn(Optional.of(testUserEntity));

		UserDetails adminDetails = toTest.loadUserByUsername(EXISTING_EMAIL);

		assertNotNull(adminDetails);
		assertEquals(EXISTING_EMAIL, adminDetails.getUsername());
		assertEquals("topsecret", adminDetails.getPassword());
		assertEquals(2, adminDetails.getAuthorities().size());
		assertRole(adminDetails.getAuthorities(), "ROLE_ADMIN");
		assertRole(adminDetails.getAuthorities(), "ROLE_USER");

	}

	private void assertRole(Collection<? extends GrantedAuthority> authorities, String role) {
		authorities.stream().filter(a -> role.equals(a.getAuthority())).findAny()
				.orElseThrow(() -> new AssertionFailedError("Role " + role + " not found!"));
	}

	@Test
	void testUserNotFound() {
		assertThrows(UsernameNotFoundException.class, () -> {
			toTest.loadUserByUsername(NOT_EXISTING_EMAIL);
		});
	}
}
