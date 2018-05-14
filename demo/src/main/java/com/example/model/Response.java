package com.example.model;

public class Response {

	String message;
	String status;
	int statusCode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public Response() {
	}

	public Response(String message, String status, int statusCode) {
		super();
		this.message = message;
		this.status = status;
		this.statusCode = statusCode;
	}

}
