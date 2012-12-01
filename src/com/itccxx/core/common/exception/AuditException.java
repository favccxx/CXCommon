package com.itccxx.core.common.exception;

public class AuditException extends CommonException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4707942469056592199L;
	
	public static final String ERROR_AUDIT_ADD_CODE = "Err_Audit_0001";
	public static final String ERROR_AUDIT_ADD_MESSAGE = "添加审计信息错误";
	
	public static final String ERROR_AUDIT_DELETE_CODE = "Err_Audit_0002";
	public static final String ERROR_AUDIT_DELETE_MESSAGE = "删除审计信息错误";
	
	public static final String ERROR_AUDIT_QUERY_CODE = "Err_Audit_0003";
	public static final String ERROR_AUDIT_QUERY_MESSAGE = "查询审计信息错误";
	

	public AuditException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
		
	}
	

}
