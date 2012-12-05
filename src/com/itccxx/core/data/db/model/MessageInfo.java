package com.itccxx.core.data.db.model;

import java.util.Date;

/**
 * 系统消息实体类
 * @author CrossCX
 *
 */
public class MessageInfo {

	//消息ID
	private String msg_id;	
	
	//消息所属模块Id
	private String msg_module_id;
	
	//消息发送系统
	private String msg_send_system;
	
	//消息标题
	private String msg_title;
	
	//消息内容
	private String msg_content;
	
	//消息详情链接
	private String msg_url;
	
	//消息发送者
	private String msg_sender;
	
	//发送类型：系统消息、邮件、站内信
	private Integer send_type;
	
	//发送日期
	private Date send_date;
	
	//消息发送状态
	private Integer msg_send_status;
	
	//消息发送频道
	private Integer msg_send_channel;
	
	//消息接收方
	private String msg_receiver;

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}

	public String getMsg_module_id() {
		return msg_module_id;
	}

	public void setMsg_module_id(String msg_module_id) {
		this.msg_module_id = msg_module_id;
	}

	public String getMsg_title() {
		return msg_title;
	}

	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}

	public String getMsg_content() {
		return msg_content;
	}

	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}

	public String getMsg_url() {
		return msg_url;
	}

	public void setMsg_url(String msg_url) {
		this.msg_url = msg_url;
	}

	public String getMsg_sender() {
		return msg_sender;
	}

	public void setMsg_sender(String msg_sender) {
		this.msg_sender = msg_sender;
	}

	public Integer getSend_type() {
		return send_type;
	}

	public void setSend_type(Integer send_type) {
		this.send_type = send_type;
	}

	public Date getSend_date() {
		return send_date;
	}

	public void setSend_date(Date send_date) {
		this.send_date = send_date;
	}

	public Integer getMsg_send_status() {
		return msg_send_status;
	}

	public void setMsg_send_status(Integer msg_send_status) {
		this.msg_send_status = msg_send_status;
	}

	public Integer getMsg_send_channel() {
		return msg_send_channel;
	}

	public void setMsg_send_channel(Integer msg_send_channel) {
		this.msg_send_channel = msg_send_channel;
	}

	public String getMsg_receiver() {
		return msg_receiver;
	}

	public void setMsg_receiver(String msg_receiver) {
		this.msg_receiver = msg_receiver;
	}

	public String getMsg_send_system() {
		return msg_send_system;
	}

	public void setMsg_send_system(String msg_send_system) {
		this.msg_send_system = msg_send_system;
	}
	
	
	

}
