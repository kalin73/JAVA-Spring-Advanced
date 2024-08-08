package com.softuni.mobilelesec.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.softuni.mobilelesec.domain.entities.OfferEntity;
import com.softuni.mobilelesec.domain.entities.UserEntity;
import com.softuni.mobilelesec.domain.entities.UserRoleEntity;
import com.softuni.mobilelesec.domain.enums.UserRoleEnum;
import com.softuni.mobilelesec.repositories.OfferRepository;
import com.softuni.mobilelesec.repositories.UserRepository;
import com.softuni.mobilelesec.repositories.UserRoleRepository;
import com.softuni.mobilelesec.services.EmailService;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "pesho@abv.bg", roles = { "USER", "ADMIN" })
public class OfferControllerIt {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private OfferRepository offerRepository;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmailService emailService;
	
	@BeforeEach
	public void setup() {
		doNothing().when(emailService).sendRegistrationEmail(null, null);
		
		var role = userRoleRepository.save(new UserRoleEntity().setRole(UserRoleEnum.ADMIN));
		
		UserEntity user = new UserEntity().setEmail("pesho@abv.bg")
				.setRoles(List.of(role))
				.setFirstName("Pesho")
				.setLastName("Ivanov")
				.setPassword("topsecret")
				.setActive(true);
		
		userRepository.save(user);
	}

	@AfterEach
	public void clean() {
		offerRepository.deleteAll();
	}

	@Test
	public void addOfferTest() throws Exception {
		mockMvc.perform(post("/offers/add")
				.accept(MediaType.APPLICATION_JSON)
				.param("model", "VW")
				.param("price", "10000")
				.param("engine", "GASOLINE")
				.param("transmission", "MANUAL")
				.param("year", "2008")
				.param("mileage", "200000")
				.param("description", "Fast!")
				.param("imageUrl", "url")
				.with(csrf()))
			.andExpect(status().is3xxRedirection());

		assertEquals(1, offerRepository.count());
		
		OfferEntity offer = offerRepository.findById(1L).get();
		
		assertEquals(new BigDecimal("10000.00"), offer.getPrice());

	}

}
