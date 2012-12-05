package com.itccxx.core.data.db.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itccxx.core.common.exception.MessageException;
import com.itccxx.core.common.util.DBUtil;
import com.itccxx.core.common.util.SqlBuildUtil;
import com.itccxx.core.common.util.StringHelper;
import com.itccxx.core.data.db.model.MessageInfo;
import com.itccxx.core.data.db.service.MessageService;

public class MessageServiceImpl implements MessageService {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private final String TABLENAME = "t_MessageInfo";
	
	@Override
	public boolean sendMessage(MessageInfo messageInfo) throws MessageException {
		try{
			conn = DBUtil.getConn();
			String sql = "insert into " + TABLENAME +" (msg_module_id,msg_send_system,msg_title,msg_content,msg_url,msg_sender,send_type,send_date,msg_send_status,msg_send_channel,msg_receiver) values(?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, messageInfo.getMsg_module_id());
			pstmt.setString(2, messageInfo.getMsg_send_system());
			pstmt.setString(3, messageInfo.getMsg_title());
			pstmt.setString(4, messageInfo.getMsg_content());
			pstmt.setString(5, messageInfo.getMsg_url());
			pstmt.setString(6, messageInfo.getMsg_sender());
			pstmt.setInt(7, messageInfo.getSend_type());
			pstmt.setDate(8, (Date) messageInfo.getSend_date());
			pstmt.setInt(9, messageInfo.getMsg_send_status());
			pstmt.setInt(10, messageInfo.getMsg_send_channel());
			pstmt.setString(11, messageInfo.getMsg_receiver());
			pstmt.executeUpdate();
			conn.commit();
			return true;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeStatement(pstmt, conn, rs);
		}
		return false;
	}
	
	@Override
	public MessageInfo[] queryMessage(MessageInfo messageInfo, int startIndex,
			int pageSize) throws MessageException {
		List<MessageInfo> list = new ArrayList<MessageInfo>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT * FROM ").append(TABLENAME).append(" ");
			if(messageInfo != null){
				sb.append(" WHERE ").append(buildMessageSQL(messageInfo));
			}
			String pagingSQL = SqlBuildUtil.buildPagingSQL(sb.toString(), startIndex, pageSize);
			conn = DBUtil.getConn();
			pstmt = conn.prepareStatement(pagingSQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MessageInfo msgInfo = new MessageInfo();
				msgInfo.setMsg_id(rs.getString("msg_id"));
				msgInfo.setMsg_module_id(rs.getString("msg_module_id"));
				msgInfo.setMsg_send_system(rs.getString("msg_send_system"));
				msgInfo.setMsg_title(rs.getString("msg_title"));
				msgInfo.setMsg_content(rs.getString("msg_content"));
				msgInfo.setMsg_url(rs.getString("msg_url"));
				msgInfo.setMsg_sender(rs.getString("msg_sender"));
				msgInfo.setSend_type(rs.getInt("send_type"));
				msgInfo.setSend_date(rs.getDate("send_date"));
				msgInfo.setMsg_send_status(rs.getInt("msg_send_status"));
				msgInfo.setMsg_send_channel(rs.getInt("msg_send_channel"));
				msgInfo.setMsg_receiver(rs.getString("msg_receiver"));
				list.add(msgInfo);
			}
		}catch(SQLException e){
			
		}finally{
			DBUtil.closeStatement(pstmt, conn, rs);
		}
		if(list.size() == 0){
			return null;
		}else{
			return (MessageInfo[]) list.toArray();
		}
	}
	
	@Override
	public long queryMessageCount(MessageInfo messageInfo)
			throws MessageException {
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT count(*) FROM ").append(TABLENAME).append(" ");
			if(messageInfo != null){
				sb.append(" WHERE ").append(buildMessageSQL(messageInfo));
			}
			conn = DBUtil.getConn();
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				return rs.getLong(1);
			}
		}catch(SQLException e){
			
		}finally{
			DBUtil.closeStatement(pstmt, conn, rs);
		}
		return 0;
	}
	
	private String buildMessageSQL(MessageInfo messageInfo){
		if(messageInfo == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		if(StringHelper.isNotEmpty(messageInfo.getMsg_id())){
			sb.append(" AND ").append(TABLENAME).append(".msg_id ='").append(messageInfo.getMsg_id()).append("'");
		}
		if(StringHelper.isNotNull(messageInfo.getMsg_send_channel())){
			sb.append(" AND ").append(TABLENAME).append(".msg_send_channel ='").append(messageInfo.getMsg_send_channel()).append("'");
		}
		if(StringHelper.isNotNull(messageInfo.getSend_type())){
			sb.append(" AND ").append(TABLENAME).append(".send_type ='").append(messageInfo.getSend_type()).append("'");
		}
		if(StringHelper.isNotNull(messageInfo.getMsg_send_status())){
			sb.append(" AND ").append(TABLENAME).append(".msg_send_status ='").append(messageInfo.getMsg_send_status()).append("'");
		}
		if(StringHelper.isNotEmpty(messageInfo.getMsg_content())){
			sb.append(" AND ").append(TABLENAME).append(".msg_content like '").append(messageInfo.getMsg_content()).append("%'");			
		}
		if(StringHelper.isNotEmpty(messageInfo.getMsg_title())){
			sb.append(" AND ").append(TABLENAME).append(".msg_title like '").append(messageInfo.getMsg_title()).append("%'");			
		}
		if(StringHelper.isNotEmpty(messageInfo.getMsg_module_id())){
			sb.append(" AND ").append(TABLENAME).append(".msg_module_id like '").append(messageInfo.getMsg_module_id()).append("%'");			
		}
		if(StringHelper.isNotEmpty(messageInfo.getMsg_sender())){
			sb.append(" AND ").append(TABLENAME).append(".msg_sender like '").append(messageInfo.getMsg_sender()).append("%'");			
		}
		if(StringHelper.isNotEmpty(messageInfo.getMsg_receiver())){
			sb.append(" AND ").append(TABLENAME).append(".msg_receiver like '").append(messageInfo.getMsg_receiver()).append("%'");			
		}
		return sb.toString();
	}

	

}
