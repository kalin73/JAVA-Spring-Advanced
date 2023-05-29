package com.softuni.mobilelesec;

import org.springframework.boot.CommandLineRunner;

import com.softuni.mobilelesec.services.EmailService;

//@Component
public class TempEmailTest implements CommandLineRunner{
	private final EmailService emailService;

	public TempEmailTest(EmailService emailService) {
		this.emailService = emailService;
	}

	@Override
	public void run(String... args) throws Exception {
		emailService.sendRegistrationEmail("kalin@example.com", "Kalin");
		
	}

}
