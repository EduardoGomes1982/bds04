package com.devsuperior.bds04.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError err = new ValidationError(
				Instant.now(),
				status.value(),
				"Validation Exception",
				e.getMessage(),
				request.getRequestURI());

		e.getBindingResult().getFieldErrors()
				.forEach(f -> err.getErrors().add(new FieldMessage(f.getField(), f.getDefaultMessage())));
		return ResponseEntity.status(status).body(err);
	}
}
