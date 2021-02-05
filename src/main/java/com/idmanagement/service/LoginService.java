package com.idmanagement.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

import com.idmanagement.dao.UserRegistrationDao;
import com.idmanagement.model.UserDetails;
import com.idmanagement.util.JwtTokenUtil;

@Service
public class LoginService {

	@Autowired
	UserRegistrationDao userRegistrationDao;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	public UserDetails getUserDetails(Authentication authentication) {
		UserDetails userDetails = null;
		try {

			DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
			Map<String, Object> attributes = oidcUser.getAttributes();
			String email = (String) attributes.get("email");

			userDetails = userRegistrationDao.getUserDetailsAndRole(email);

			if (null != userDetails) {
				String token = jwtTokenUtil.generateToken(userDetails);
			}

		} catch (Exception e) {

		}
		return userDetails;

	}

	private String genearteToken(UserDetails userDetails) {
		try {

			new User(userDetails.getUsername(), null, new ArrayList<>());

		} catch (Exception e) {

		}
		return null;
	}

}
