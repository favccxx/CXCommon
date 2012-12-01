package com.itccxx.core.data.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itccxx.core.common.exception.AuditException;
import com.itccxx.core.common.exception.InvalidParameter;
import com.itccxx.core.common.util.DBUtil;
import com.itccxx.core.common.util.SqlBuildUtil;
import com.itccxx.core.common.util.Utils;
import com.itccxx.core.data.db.inf.AuditService;
import com.itccxx.core.data.db.model.AuditInfo;

public class AuditServiceImpl implements AuditService {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private final String TABLENAME = "AuditInfo";
	

	@Override
	public void addAudit(AuditInfo auditInfo) throws AuditException {
		if(Utils.isEmpty(auditInfo.getUserId()) || 
				Utils.isEmpty(auditInfo.getModule_name()) || 
				Utils.isEmpty(auditInfo.getFunction_name()) || 
				auditInfo.getAudit_date() == null){
			//TODO: 抛出参数不正确的异常信息
		}
		
		String sql = "Insert into " + TABLENAME + " (USERID,MODULE_NAME,FUNCTION_NAME,OPERATION,AUDIT_DATE,MEMO) values " +
				"(?,?,?,?,to_date(?,'yyyy-MM-dd hh24:mi:ss'),?)";
		
		try {
			conn = DBUtil.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, auditInfo.getUserId());
			pstmt.setString(2, auditInfo.getModule_name());
			pstmt.setString(3, auditInfo.getFunction_name());
			pstmt.setString(4, auditInfo.getOperation());
			pstmt.setString(5, auditInfo.getAudit_date());
			pstmt.setString(6, auditInfo.getMemo());
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AuditException(AuditException.ERROR_AUDIT_ADD_CODE,AuditException.ERROR_AUDIT_ADD_MESSAGE);
		}finally{
			DBUtil.closeStatement(pstmt, conn, null);
		}
	}

	@Override
	public AuditInfo[] queryAuditInfoByModule(String userId, String moduleName,
			String operation, Date startDate, Date endDate, String memo,
			int startIndex, int count) throws AuditException {
		AuditInfo[] auditArray = null;
		try {
			 DateFormat df = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
			
			 String strStartDate = "";
			 String strEndDate = "";
			 if(startDate != null){
				 strStartDate = df.format(startDate);
			 }
			 if(endDate != null){
				 strEndDate = df.format(endDate);
			 }
			 String sql = "select * from "+TABLENAME;
			 if(!Utils.isEmpty(userId) && !Utils.isEmpty(moduleName) && !Utils.isEmpty(operation) && startDate!= null && endDate!=null){
				 sql += " where ";
			 }
			 sql = SqlBuildUtil.addSingleAndCondition(sql, "MODULE_NAME", moduleName);
			 sql = SqlBuildUtil.addSingleAndCondition(sql, "OPERATION", operation);
			 sql = SqlBuildUtil.addSingleAndCondition(sql, "USERID", userId);
			 sql = SqlBuildUtil.addDateTimeCondition(sql, "AUDIT_DATE", strStartDate, strEndDate);
			 
			 conn = DBUtil.getConn();
			 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 List<AuditInfo> auditList = new ArrayList<AuditInfo>();
			 int countForArray = 0;
			 while(rs.next()){
				 AuditInfo auditInfo = new AuditInfo();
				 auditInfo.setUserId(rs.getString(1));
				 auditInfo.setModule_name(rs.getString(2));
				 auditInfo.setFunction_name(rs.getString(3));
				 auditInfo.setOperation(rs.getString(4));
				 auditInfo.setAudit_date(rs.getDate(5).toString());
				 auditInfo.setMemo(rs.getString(6));
				 auditList.add(auditInfo);
				 countForArray ++;
			 }
			 
			auditArray = new AuditInfo[countForArray];
			for(int i=0;i<auditList.size();i++){
				auditArray[i] = auditList.get(i);
			}	
			
		} catch (InvalidParameter e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeStatement(pstmt, conn, rs);
		}
		return auditArray;
	}

}
