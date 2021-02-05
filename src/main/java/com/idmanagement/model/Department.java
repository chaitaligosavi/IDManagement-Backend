package com.idmanagement.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Department {

	private static List<String> departments = new ArrayList<>();
	private static Map<String, Integer> departmentId = new HashMap<>();

	static {
		departments.add("TECHNOLOGY");
		departments.add("PMO");
		departments.add("BSG");

		departmentId.put("TECHNOLOGY", 1);
		departmentId.put("PMO", 2);
		departmentId.put("BSG", 3);
	}

	public static List<String> getDeaprtments() {
		return departments;
	}

	public static Map<String, Integer> getDepartmentId() {
		return departmentId;
	}

	public static boolean isDepartmentAllowed(String department) {
		return departments.contains(department.toUpperCase());
	}

}
