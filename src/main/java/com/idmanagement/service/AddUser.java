package com.idmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.idmanagement.dao.UserRegistrationDao;
import com.idmanagement.exception.BaseServiceException;
import com.idmanagement.exception.model.ApiFieldError;
import com.idmanagement.exception.model.ApiGlobalError;
import com.idmanagement.inter.RequestProcessor;
import com.idmanagement.model.Department;
import com.idmanagement.model.Role;
import com.idmanagement.model.UserDetails;
import com.idmanagement.util.ResponseUtil;

@Service
public class AddUser implements RequestProcessor {

	@Autowired
	UserRegistrationDao userRegistrationDao;

	@Override
	public Object process(UserDetails userDetails) throws Exception {

		try {
			// validate request
			validate(userDetails);

			// save the user
			if (userRegistrationDao.addUserDetails(userDetails) == 0) {
				throw new BaseServiceException("Failed to add user", 500);
			}

		} catch (BaseServiceException be) {
			throw be;
		}

		catch (Exception e) {
			throw new BaseServiceException("Technical issue while adding user", 500);
		}
		return ResponseUtil.getCustomResponse(200, "User added successfuly", "");

	}

	@Override
	public void validate(UserDetails userDetails) throws Exception {

		List<ApiFieldError> errorList = new ArrayList<>();// add validation error

		try {

			if (StringUtils.isEmpty(userDetails.getEmail())) {
				errorList.add(new ApiFieldError("email", "email is required"));
			}

			else if (!Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$")
					.matcher(userDetails.getEmail()).matches()) {
				errorList.add(new ApiFieldError("email", "invalid email format"));

			}

			if (StringUtils.isEmpty(userDetails.getMobile())) {
				errorList.add(new ApiFieldError("mobile", "mobile is required"));

			}

			else if (!NumberUtils.isNumber(userDetails.getMobile()) || userDetails.getMobile().length() != 10) {
				errorList.add(new ApiFieldError("mobile", "mobile number is invalid"));
			}

			if (StringUtils.isEmpty(userDetails.getRole())) {
				errorList.add(new ApiFieldError("role", "role is required"));

			}

			else if (!Role.isRoleAllowed(userDetails.getRole())) {
				errorList.add(new ApiFieldError("role", "role" + userDetails.getRole() + " is invalid"));
			}

			if (StringUtils.isEmpty(userDetails.getResidentialAddress())) {
				errorList.add(new ApiFieldError("residentialAddress", "residential address is required"));

			}

			else if (userDetails.getResidentialAddress().length() > 500) {
				errorList.add(new ApiFieldError("residentialAddress",
						"residential address length cannot be grater than 500"));

			}

			if (StringUtils.isEmpty(userDetails.getDepartment())) {
				errorList.add(new ApiFieldError("department", "department is required"));

			}

			else if (!Department.isDepartmentAllowed(userDetails.getDepartment())) {
				errorList.add(new ApiFieldError("department", "invalid department"));
			}

			if (StringUtils.isEmpty(userDetails.getUsername())) {
				errorList.add(new ApiFieldError("username", "username is required"));

			}

			else if (!NumberUtils.isNumber(userDetails.getUsername()) || userDetails.getUsername().length() != 6) {
				errorList.add(new ApiFieldError("username", "username is invalid"));
			}

			if (!errorList.isEmpty()) {
				// throw exception if validation falis
				throw new ApiGlobalError("Validation error", 403, errorList);
			}

		} catch (BaseServiceException be) {
			throw be;
		}

		catch (Exception e) {

			throw new BaseServiceException("There seems technical issue", 500);

		}

	}

}
