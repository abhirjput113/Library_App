package com.abc.lib.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.abc.lib.exceptions.BookNotFoundException;
import com.abc.lib.exceptions.MemberNotFoundException;
import com.abc.lib.model.ErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler {

	private ErrorResponse getErrorResponse(String type, Integer status, String title, String message, String uri) {

		ErrorResponse errorResponse = new ErrorResponse(type, status, title, message, uri);
		return errorResponse;
	} 

	@ExceptionHandler
	private ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException bnfe, WebRequest request) {

		String type = "https://httpstatuses.com/";
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		Integer status = httpStatus.value();
		String title = httpStatus.name();
		String message = bnfe.getLocalizedMessage();
		String uri = request.getDescription(false);

		
		ErrorResponse errorResponse = getErrorResponse(type, status, title, message, uri);
		return new ResponseEntity<ErrorResponse>(errorResponse, httpStatus);

	}
	
	@ExceptionHandler
	private ResponseEntity<ErrorResponse> handleMemberNotFoundException(MemberNotFoundException mnfe, WebRequest request) {

		String type = "https://httpstatuses.com/";
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		Integer status = httpStatus.value();
		String title = httpStatus.name();
		String message = mnfe.getLocalizedMessage();
		String uri = request.getDescription(false);

		
		ErrorResponse errorResponse = getErrorResponse(type, status, title, message, uri);
		return new ResponseEntity<ErrorResponse>(errorResponse, httpStatus);

	}
}