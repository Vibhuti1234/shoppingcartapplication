package com.mindtree.cartapplication.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.cartapplication.controller.ShoppingCartApplicationController;
import com.mindtree.cartapplication.dto.ErrorDto;
import com.mindtree.cartapplication.exception.ServiceException;

@RestControllerAdvice(assignableTypes= {ShoppingCartApplicationController.class})
public class ControllerExceptionHandler {
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ErrorDto> serviceExceptionHandler(Exception e, Throwable cause){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(e.getMessage(), cause));
	}

}