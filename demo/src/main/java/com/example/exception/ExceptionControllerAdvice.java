package com.example.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.ErrorResponse;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@Value("${exMsg}")
	private String exMsg;
	
	@Value("${exStatus}")
	private String exStatus;
	
	@Value("${exStatusCode}")
	private int exStatusCode;
	
	
	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public ErrorResponse exception(Exception ex) {
		ex.printStackTrace();
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(exMsg);
		errorResponse.setStatus(exStatus);
		errorResponse.setStatusCode(exStatusCode);
		return errorResponse;
	}
	
	@ExceptionHandler(value = { NoRecordFoundException.class })
	@ResponseBody
	public ErrorResponse exception(NoRecordFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatus(exStatus);
		errorResponse.setStatusCode(exStatusCode);
		return errorResponse;
	}
}
