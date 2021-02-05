package com.idmanagement.model;

import java.sql.Timestamp;
import java.util.Map;

public class UserDetails {

	private String email;
	private String username;
	private String residentialAddress;
	private String department;
	private Timestamp hireDate;
	private String role;
	private String name;
	private String mobile;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Map<String, Object> claims;

	public Map<String, Object> getClaims() {
		return claims;
	}

	public void setClaims(Map<String, Object> claims) {
		this.claims = claims;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}

	public String getDepartment() {
		return department;
	}

	public Timestamp getHireDate() {
		return hireDate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setHireDate(Timestamp hireDate) {
		this.hireDate = hireDate;
	}

}
