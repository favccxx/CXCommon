package com.itccxx.core.common.exception;

public class InvalidParameter extends CommonException {
	
	private static final long serialVersionUID = 2268473864738902667L;
	

	public InvalidParameter(String errorCode, String errorMessage) {
		
		super(errorCode, CommonExceptionInfo.COMMON_ERR_MSG_INVALID_PARAMETER + "\n" + errorMessage);
	}
	
	public InvalidParameter(String errorMessage) {
		
		super(CommonExceptionInfo.COMMON_ERR_MSG_INVALID_PARAMETER + "\n" + errorMessage);
	}
	

}
