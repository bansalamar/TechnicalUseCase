package com.example.exception;

public class NoRecordFoundException extends RuntimeException {
	private static final long serialVersionUID = 9876543456789L;

	public NoRecordFoundException(String msg) {
		super(msg);
	}
}
