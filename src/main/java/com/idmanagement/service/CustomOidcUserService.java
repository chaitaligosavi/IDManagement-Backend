// package com.idmanagement.service;
//
// import java.util.Map;
//
// import
// org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
// import
// org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
// import
// org.springframework.security.oauth2.core.OAuth2AuthenticationException;
// import org.springframework.security.oauth2.core.oidc.user.OidcUser;
// import org.springframework.stereotype.Service;
//
// @Service
// public class CustomOidcUserService extends OidcUserService {
//
// @Override
// public OidcUser loadUser(OidcUserRequest userRequest) throws
// OAuth2AuthenticationException {
// OidcUser oidcUser = super.loadUser(userRequest);
// Map attributes = oidcUser.getAttributes();
//
// // userInfo.setEmail((String) attributes.get("email"));
// // userInfo.setId((String) attributes.get("sub"));
// // userInfo.setImageUrl((String) attributes.get("picture"));
// // userInfo.setName((String) attributes.get("name"));
// // updateUser(userInfo);
// return oidcUser;
// }
// }
