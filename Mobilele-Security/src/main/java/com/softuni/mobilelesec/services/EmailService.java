package com.softuni.mobilelesec.services;

import java.util.Locale;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	private final JavaMailSender javaMailSender;
	private final TemplateEngine templateEngine;

	public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
		this.javaMailSender = javaMailSender;
		this.templateEngine = templateEngine;
	}

	public void sendRegistrationEmail(String userEmail, String username) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

		try {
			mimeMessageHelper.setFrom("mobilele@example.com");
			mimeMessageHelper.setTo(userEmail);
			mimeMessageHelper.setSubject("Welcome to Mobilele!");
			mimeMessageHelper.setText(generateEmailText(username), true);

			javaMailSender.send(mimeMessage);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	private String generateEmailText(String username) {
		Context context = new Context();
		context.setLocale(Locale.getDefault());
		context.setVariable("username", username);

		return templateEngine.process("email/registration", context);
	}
}
