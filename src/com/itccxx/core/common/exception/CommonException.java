package com.itccxx.core.common.exception;

public class CommonException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6849082021750128409L;

	private String error_code;
	
	private String error_message;

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	
	public CommonException(String errorMessage){
		this.error_message = errorMessage;
	}
	
	public CommonException(String errorCode, String errorMessage){
		this.error_code = errorCode;
		this.error_message = errorMessage;
	}
	
	public CommonException(Throwable cause){
		super(cause);
	}
	
	
}
