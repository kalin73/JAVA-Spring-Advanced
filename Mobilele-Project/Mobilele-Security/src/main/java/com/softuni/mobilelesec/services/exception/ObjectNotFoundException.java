package com.softuni.mobilelesec.services.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private final UUID offerId;

	public ObjectNotFoundException(String message, UUID offerId) {
		super(message);
		this.offerId = offerId;
	}

	public UUID getOfferId() {
		return offerId;
	}
	
}
