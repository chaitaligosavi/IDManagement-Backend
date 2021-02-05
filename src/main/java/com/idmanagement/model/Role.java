package com.idmanagement.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Role {

	private static List<String> roles = new ArrayList<>();
	private static Map<String, Integer> roleId = new HashMap<>();

	static {
		roles.add("ADMIN");
		roles.add("USER");

		roleId.put("ADMIN", 1);
		roleId.put("USER", 2);
	}

	public static List<String> getAllowedRoles() {
		return roles;
	}

	public static Map<String, Integer> getRoleId() {
		return roleId;
	}

	public static boolean isRoleAllowed(String frequency) {
		return roles.contains(frequency.toUpperCase());
	}

}
