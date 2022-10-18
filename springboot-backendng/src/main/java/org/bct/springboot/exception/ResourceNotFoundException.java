package org.bct.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	//The following method describes the message if the resource(data in DB)is not found
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
