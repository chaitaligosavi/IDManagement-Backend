package com.idmanagement.exception;

import java.util.List;

import com.idmanagement.exception.model.ApiFieldError;

public class BaseServiceException extends Exception {

	private static final long serialVersionUID = 7718828512143293558L;

	private Integer code = ErrorCodeMapping.SERVICE_FAILURE_EXCEPTION.getValue();

	List<ApiFieldError> errors;

	public BaseServiceException(Integer code) {
		super();
		this.code = code;
	}

	public BaseServiceException() {
		super();
	}

	public BaseServiceException(String message, Throwable cause, Integer code) {
		super(message, cause);
		this.code = code;
	}

	public BaseServiceException(String message, Integer code) {
		super(message);
		this.code = code;
	}

	public BaseServiceException(Throwable cause, Integer code) {
		super(cause);
		this.code = code;
	}

	public Integer getCode() {
		return this.code;
	}

	public List getErrors() {
		return errors;
	}

	public void setErrors(List<ApiFieldError> errors) {
		this.errors = errors;
	}

	public BaseServiceException(String message, List<ApiFieldError> errors) {
		super(message);
		this.errors = errors;

	}

	public BaseServiceException(String message, Integer code, List<ApiFieldError> errors) {
		super(message);
		this.errors = errors;
		this.code = code;
	}
}