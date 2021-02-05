package com.idmanagement.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.idmanagement.model.CustomResponse;

public class ResponseUtil {
	@Autowired
	static HttpServletRequest httpServletRequest;

	public static CustomResponse<Object> getCustomResponse(int statusCode, Object data, String description) {
		//
		CustomResponse<Object> customResponse = new CustomResponse<Object>(statusCode, description, data,
				System.currentTimeMillis(), null);

		return customResponse;
	}

	private ResponseUtil() {

	}

}
