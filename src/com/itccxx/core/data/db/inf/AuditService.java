package com.itccxx.core.data.db.inf;

import java.util.Date;

import com.itccxx.core.common.exception.AuditException;
import com.itccxx.core.data.db.model.AuditInfo;

/**
 * 当前审计信息只支持添加和删除
 * @author CrossCX
 *
 */
public interface AuditService {
	
	/**
	 * 添加一条审计信息
	 * @param auditInfo 审计信息
	 * @throws AuditException
	 */
	public void addAudit(AuditInfo auditInfo)throws AuditException;
	
	/**
	 * 查询审计信息
	 * @param userId	用户Id
	 * @param moduleName	模块名称
	 * @param operation	操作名称
	 * @param startDate	起始日期
	 * @param endDate	终止日期
	 * @param memo	审计内容
	 * @param startIndex 从多少条记录开始查询，startIndex<=0 或者为null，从第一条开始查询
	 * @param count	本次查询读取记录的数量，如为count<=0 或者为Nll,默认查询500条
	 * @return
	 * @throws AuditException
	 */
	public AuditInfo[] queryAuditInfoByModule(String userId,String moduleName, String operation, Date startDate,Date endDate,String memo,int startIndex,int count)throws AuditException;
	
	
	
}
