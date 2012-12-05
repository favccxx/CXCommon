package com.itccxx.core.data.db.service;

import com.itccxx.core.common.exception.MessageException;
import com.itccxx.core.data.db.model.MessageInfo;

public interface MessageService {
	
	/**
	 * 添加一条用户发送消息
	 * @param messageInfo 消息实体类
	 * @return
	 */
	public boolean sendMessage(MessageInfo messageInfo)throws MessageException;
	
	/**
	 * 根据消息中的所有参数对消息，查询消息记录，条件之间使用And连接符
	 * @param messageInfo 消息实体类，如有值，则根据该值进行条件查询
	 * @param startIndex	起始索引
	 * @param pageSize	当前页大小
	 * @return
	 */
	public MessageInfo[] queryMessage(MessageInfo messageInfo,int startIndex,int pageSize)throws MessageException;
	
	/**
	 * 根据消息中的记录总数，所有非空参数通过ＡＮＤ的关系来查询符合条件的记录。
	 * @param messageInfo 消息实体类，
	 * @return
	 */
	public long queryMessageCount(MessageInfo messageInfo)throws MessageException;

	
}
