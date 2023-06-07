package com.softuni.mobilelesec.web;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;

import jakarta.mail.internet.MimeMessage;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Value("${mail.host}")
	private String mailHost;
	
	@Value("${mail.port}")
	private Integer mailPort;
	
	@Value("${mail.username}")
	private String username;
	
	@Value("${mail.password}")
	private String password;
	
	
	private GreenMail greenMail;
	
	@BeforeEach
	void setUp() {
		greenMail = new GreenMail(new ServerSetup(mailPort, mailHost, "smtp"));
		greenMail.start();
		greenMail.setUser(username, password);
	}
	
	@AfterEach
	void tearDown() {
		greenMail.stop();
	}

	@Test
	void testRegistration() throws Exception {
	    mockMvc.perform(post("/users/register").
	        param("email", "pesho@example.com").
	        param("firstName", "Pesho").
	        param("lastName", "Petrov").
	        param("password", "topsecret").
	        param("confirmPassword", "topsecret").
	        with(csrf()))
	    .andExpect(status().is3xxRedirection())
	    .andExpect(redirectedUrl("/users/login"));
	    
	    MimeMessage[] mails = greenMail.getReceivedMessages();
	    assertEquals(1, mails.length);
	    
	    MimeMessage welcomeMessage = mails[0];
	    assertTrue(welcomeMessage.getContent().toString().contains("Pesho Petrov"));
	}
}