package com.idmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.idmanagement.filter.JwtTokenAuthenticationProcessingFilter;

@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	JwtTokenAuthenticationProcessingFilter jwtTokenAuthenticationProcessingFilter;

	public static final String TOKEN_REFRESH_ENTRY_POINT = "/restricted";
	public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/not-restricted";

	// protected JwtTokenAuthenticationProcessingFilter
	// buildJwtTokenAuthenticationProcessingFilter() throws Exception {
	// List<String> pathsToSkip = Arrays.asList(TOKEN_REFRESH_ENTRY_POINT);
	// SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip,
	// TOKEN_BASED_AUTH_ENTRY_POINT);
	// JwtTokenAuthenticationProcessingFilter filter = new
	// JwtTokenAuthenticationProcessingFilter();
	//
	// return filter;
	// }

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and().formLogin().disable()
				.httpBasic().disable().logout().disable().csrf().disable().

				addFilterBefore(jwtTokenAuthenticationProcessingFilter, UsernamePasswordAuthenticationFilter.class).

				authorizeRequests().antMatchers(new String[] { "/", "/api/add/user", "/api/fetch/user" }).permitAll()
				.anyRequest().authenticated().and().oauth2Login().redirectionEndpoint().baseUri("/oauth2/callback/*")
				.and().userInfoEndpoint();

	}

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) {
	// auth.authenticationProvider(new
	// OAuth2LoginAuthenticationProvider(accessTokenResponseClient, userService));
	// }

	//
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
