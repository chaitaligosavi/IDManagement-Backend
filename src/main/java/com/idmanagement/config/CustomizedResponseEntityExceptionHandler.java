package com.idmanagement.config;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.idmanagement.exception.BaseServiceException;
import com.idmanagement.model.CustomResponse;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@Autowired
	HttpServletRequest servletRequest;

	@ExceptionHandler(value = BaseServiceException.class)
	protected ResponseEntity<CustomResponse<Object>> handleBaseException(BaseServiceException ex) {

		String description = "please try again";

		CustomResponse<Object> customResponse = new CustomResponse<>(ex.getCode(), description,
				Objects.nonNull(ex.getErrors()) ? ex.getErrors() : ex.getMessage(), System.currentTimeMillis(),
				servletRequest.getServletPath());
		return new ResponseEntity<>(customResponse, HttpStatus.OK);
	}

	@ExceptionHandler(value = Exception.class)
	protected ResponseEntity<CustomResponse<Object>> handleException(Exception ex) {

		String description = "please try again";

		CustomResponse<Object> customResponse = new CustomResponse<>(500, description, Objects.nonNull(ex.getMessage()),
				System.currentTimeMillis(), servletRequest.getServletPath());
		return new ResponseEntity<>(customResponse, HttpStatus.OK);
	}

}
