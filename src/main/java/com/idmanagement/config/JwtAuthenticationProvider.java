package com.idmanagement.config;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.idmanagement.util.JwtTokenUtil;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	public HttpServletRequest httpServletRequest;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Override
	public Authentication authenticate(Authentication arg0) throws AuthenticationException {
		try {
			Map<String, String> headers = getHeaders(httpServletRequest);

			jwtTokenUtil.validateToken(headers.get("token"), "166159");

		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public Map<String, String> getHeaders(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		return map;
	}

}
