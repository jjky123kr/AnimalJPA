package com.Animal.blog.handler;

import java.sql.PseudoColumnUsage;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.Animal.blog.DTO.ResponseDto;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	/*
	 * @ExceptionHandler(value=IllegalArgumentException.class) public String
	 * handleArgumentException(IllegalArgumentException e) {
	 * return"<h1>"+e.getMessage()+"</h1>"; }
	 */
	
	@ExceptionHandler(value=Exception.class)
	public ResponseDto<String>handleArgumentException(Exception e){
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());		
	}
}
