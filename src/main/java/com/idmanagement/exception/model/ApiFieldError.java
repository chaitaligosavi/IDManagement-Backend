package com.idmanagement.exception.model;

public class ApiFieldError {
	private String field;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ApiFieldError(String field, String message) {
		this.field = field;

		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "ApiFieldError [field=" + field + ", message=" + message + "]";
	}

}
