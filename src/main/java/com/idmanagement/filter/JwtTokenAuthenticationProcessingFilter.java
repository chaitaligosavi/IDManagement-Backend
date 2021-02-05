package com.idmanagement.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.idmanagement.dao.UserRegistrationDao;
import com.idmanagement.exception.BaseServiceException;
import com.idmanagement.model.UserDetails;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtTokenAuthenticationProcessingFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtilFilter jwtTokenUtil;

	@Autowired
	UserRegistrationDao userRegistrationDao;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");

				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token has expired");

			}

		} else {
			logger.warn("JWT Token does not begin with Bearer String");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token has expired");
		}
		UserDetails userDetails = null;
		if (!StringUtils.isEmpty(username)) {

			try {
				userDetails = userRegistrationDao.getUserDetailsAndRole(username);
			} catch (BaseServiceException e) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "invalid user");
			}

			if (jwtTokenUtil.validateToken(jwtToken, userDetails.getEmail())) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, new ArrayList<>());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			chain.doFilter(request, response);
		}

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

		List<String> pathsToSkip = Arrays.asList("/restricted");

		return pathsToSkip.stream().anyMatch(p -> new AntPathMatcher().match(p, request.getServletPath()));

	}
}
