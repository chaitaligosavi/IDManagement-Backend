package com.idmanagement.exception;

/**
 * <p>
 *   Interface to be implemented by 
 *   all custom exceptions which want to provide 
 *   their own custom error codes and error messages
 * </p>
 *
 */
public interface IExceptionData 
{  
	/**
	 * <p>
	 *   Fetch exception error message. This field will populate the 
	 *   status description field
	 * </p>
	 * @return
	 */
	public String fetchDetailedErrorMessage();
	
	/**
	 * <p>
	 *    Fetch the brief summary of the exception message, it could 
	 *    also be a code
	 * </p>
	 * @return
	 */
	public String fetchSummaryErrorMessage();
}	