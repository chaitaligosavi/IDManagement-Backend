package com.idmanagement.exception.model;

import java.util.List;

import com.idmanagement.exception.BaseServiceException;

public class ApiGlobalError extends BaseServiceException {

	private static final long serialVersionUID = 1L;

	List<ApiFieldError> errors;

	public ApiGlobalError(String message, Integer code, List<ApiFieldError> errors) {
		super(message, code, errors);
		this.errors = errors;
	}

	public List<ApiFieldError> getErrors() {
		return errors;
	}

	public void setError(List<ApiFieldError> errors) {
		this.errors = errors;
	}

}
