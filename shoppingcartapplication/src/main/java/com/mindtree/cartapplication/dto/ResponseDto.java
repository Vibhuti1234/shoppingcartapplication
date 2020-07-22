package com.mindtree.cartapplication.dto;


public class ResponseDto<T> {

	private T data;
	private ErrorDto error;
	private String message;
	private boolean success;
	
	public ResponseDto() {
		
	}

	public ResponseDto(T data, ErrorDto error, String message, boolean success) {
		super();
		this.data = data;
		this.error = error;
		this.message = message;
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ErrorDto getError() {
		return error;
	}

	public void setError(ErrorDto error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}