package com.idmanagement.exception;

public enum ErrorCodeMapping {

     INVALID_KEY_EXCEPTION(403),
     KEY_EXPIRED_EXCEPTION(401),
     BAD_REQUEST_EXCEPTION(400),
     UNAUTHORIZED_EXCEPTION(401),
     NOT_FOUND_EXCEPTION(404),
     NOT_ALLOWED_EXCEPTION(405),
     METHOD_NOT_FOUND_EXCEPTION(404),
     REQUEST_TIMEOUT_EXCEPTION(408),
     SERVICE_FAILURE_EXCEPTION(500),
     FIELD_FAILURE_EXCEPTION(403),
     DB_FAILURE_EXCEPTION(500),
     INVALID_TOKEN_EXCEPTION(204),
		SERVICE_NOT_FOUNT(503);

     private final int value;

     ErrorCodeMapping(final int newValue) {
         value = newValue;
     }

    public int getValue() {
	return value;}
}
