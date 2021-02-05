package com.idmanagement.exception;

/**
 * <p>
 * Base exception class for all Custom Exceptions
 * </p>
 * 
 *
 */
public class BaseException extends RuntimeException implements IExceptionData {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String detailedErrorMessage;

	private String summaryErrorMessage;

	/**
	 * <p>
	 * Create exception with message
	 * </p>
	 * 
	 * @param message
	 */
	public BaseException(String message) {
		super(message);
	}

	/**
	 * <p>
	 * Create exception with message and throwable
	 * </p>
	 * 
	 * @param message
	 */
	public BaseException(String message, Throwable e) {
		super(message, e);
	}

	public String fetchDetailedErrorMessage() {
		if(detailedErrorMessage == null)
			detailedErrorMessage = super.getMessage(); 
		
		return detailedErrorMessage;
	}

	public String fetchSummaryErrorMessage() {
		if(summaryErrorMessage == null)
			summaryErrorMessage = super.getMessage(); 
		
		return summaryErrorMessage;
	}

	public void setDetailedErrorMessage(String detailedErrorMessage) {
		this.detailedErrorMessage = detailedErrorMessage;
	}

	public void setSummaryErrorMessage(String summaryErrorMessage) {
		this.summaryErrorMessage = summaryErrorMessage;
	}

}