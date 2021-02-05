package com.idmanagement.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.idmanagement.exception.BaseServiceException;
import com.idmanagement.model.Department;
import com.idmanagement.model.Role;
import com.idmanagement.model.UserDetails;

@Repository
public class UserRegistrationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public UserDetails getUserDetailsAndRole(String email) throws BaseServiceException {
		UserDetails userDetails = null;
		try {

			String query = "select u.username,u.email,u.mobile,u.residential_address,d.department_code as department,u.hire_date,r.role_code as role from user u, role r, department d where u.email= ? and u.role_id = r.id and u.department_id= d.id;";
			userDetails = (UserDetails) jdbcTemplate.queryForObject(query, new Object[] { email },
					new BeanPropertyRowMapper(UserDetails.class));

			if (null != userDetails) {
				Map<String, Object> claims = new HashMap<>();
				claims.put("ROLE", userDetails.getRole());
				claims.put("USER", userDetails.getUsername());
				userDetails.setClaims(claims);
			}

		} catch (EmptyResultDataAccessException ee) {
			throw new BaseServiceException("No record found", 500);
		} catch (Exception e) {
			System.out.println("Exception inside getUserDetailsAndRole -- >" + e);

		}
		return userDetails;
	}

	@Transactional
	public int addUserDetails(UserDetails userDetails) throws BaseServiceException {
		int isInserted = 0;
		try {

			String query = "insert into user(username,email,mobile,residential_address,is_active,role_id,department_id) values(?,?,?,?,?,?,?)";
			isInserted = jdbcTemplate.update(query, userDetails.getUsername(), userDetails.getEmail(),
					userDetails.getMobile(), userDetails.getResidentialAddress(), 1,
					Role.getRoleId().get(userDetails.getRole()),
					Department.getDepartmentId().get(userDetails.getDepartment()));

			System.out.println("user inserted " + isInserted);

		} catch (DuplicateKeyException de) {
			throw new BaseServiceException("User already present", 400);
		}

		catch (Exception e) {
			throw new BaseServiceException();

		}
		return isInserted;
	}

	public UserDetails fetchUserDetailsWithUsername(String username) throws BaseServiceException {
		UserDetails userDetails = null;
		try {

			String query = "select u.username,u.email,u.mobile,u.residential_address,d.department_code as department,u.hire_date,r.role_code as role from user u, role r, department d where u.username= ? and u.role_id = r.id and u.department_id= d.id";
			userDetails = (UserDetails) jdbcTemplate.queryForObject(query, new Object[] { username },
					new BeanPropertyRowMapper(UserDetails.class));

		} catch (EmptyResultDataAccessException ee) {
			throw new BaseServiceException("No record found", 500);
		}

		catch (Exception e) {
			System.out.println("Exception inside getUserDetailsAndRole -- >" + e);

		}
		return userDetails;
	}

}
