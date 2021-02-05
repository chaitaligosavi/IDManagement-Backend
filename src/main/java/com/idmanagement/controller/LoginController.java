package com.idmanagement.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.idmanagement.exception.BaseServiceException;
import com.idmanagement.model.CustomResponse;
import com.idmanagement.model.UserDetails;
import com.idmanagement.service.AddUser;
import com.idmanagement.service.FetchUser;
import com.idmanagement.service.LoginService;
import com.idmanagement.util.JwtTokenUtil;

@RestController
public class LoginController<E> {

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	LoginService loginService;

	@Autowired
	AddUser addUser;

	@Autowired
	FetchUser fetchUser;

	@GetMapping("/")
	public String helloWorld() {
		return "you don't need to be logged in";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/api/add/user", produces = { "application/json" })
	public CustomResponse<E> addUser(@RequestBody UserDetails userDetails) throws Exception {

		try {

			return (CustomResponse<E>) addUser.process(userDetails);
		}

		catch (BaseServiceException e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/api/fetch/user", produces = { "application/json" })
	public CustomResponse<E> fetchUser(@RequestBody UserDetails userDetails) throws Exception {

		try {

			return (CustomResponse<E>) fetchUser.process(userDetails);
		}

		catch (BaseServiceException e) {
			throw e;
		}
	}

	@GetMapping("/restricted")
	public void restricted(HttpServletResponse response, Authentication authentication) throws IOException {
		// System.out.println(oidcUser);
		// UserDetails userDetails= new UserDetails();
		// jwtTokenUtil.generateToken(userDetails)
		try {
			UserDetails userDetails = loginService.getUserDetails(authentication);
			String token = jwtTokenUtil.generateToken(userDetails);

			UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("http").host("localhost:4200")
					.path("/#/login").query("username={keyword}&token={keyword1}&role={keyword2}")
					.buildAndExpand(userDetails.getUsername(), token, userDetails.getRole());

			String url = String.valueOf(uriComponents);

			response.sendRedirect(url);
		} catch (Exception e) {

		}

	}

}
