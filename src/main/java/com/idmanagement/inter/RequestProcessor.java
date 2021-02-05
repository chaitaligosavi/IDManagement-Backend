package com.idmanagement.inter;

import com.idmanagement.model.UserDetails;

public interface RequestProcessor {

	public void validate(UserDetails userDetails) throws Exception;

	public Object process(UserDetails userDetails) throws Exception;
}
