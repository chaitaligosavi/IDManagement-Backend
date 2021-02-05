package com.idmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.idmanagement.dao.UserRegistrationDao;
import com.idmanagement.exception.BaseServiceException;
import com.idmanagement.exception.model.ApiFieldError;
import com.idmanagement.exception.model.ApiGlobalError;
import com.idmanagement.inter.RequestProcessor;
import com.idmanagement.model.UserDetails;
import com.idmanagement.util.ResponseUtil;

@Service
public class FetchUser implements RequestProcessor {

	@Autowired
	UserRegistrationDao userRegistrationDao;

	@Override
	public Object process(UserDetails userDetails) throws Exception {
		UserDetails userDetailsRetrived = null;
		try {
			// validate request
			validate(userDetails);

			// fetch user
			userDetailsRetrived = userRegistrationDao.fetchUserDetailsWithUsername(userDetails.getUsername());

			if (null == userDetailsRetrived) {
				new BaseServiceException("No record found", 500);
			}

		} catch (BaseServiceException be) {
			throw be;
		}

		catch (Exception e) {
			throw new BaseServiceException("Technical issue while fetching user", 500);

		}
		return ResponseUtil.getCustomResponse(200, userDetailsRetrived, "User fetchd Successfuly");

	}

	@Override
	public void validate(UserDetails userDetails) throws Exception {
		List<ApiFieldError> errorList = new ArrayList<>();// add validation error
		try {
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
