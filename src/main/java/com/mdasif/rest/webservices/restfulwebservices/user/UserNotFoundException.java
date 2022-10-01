package com.mdasif.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = -470180507998010368L;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(final String message) {
		super(message);
	}

}
