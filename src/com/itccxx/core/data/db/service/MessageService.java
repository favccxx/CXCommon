package com.itccxx.core.data.db.service;

import com.itccxx.core.common.exception.MessageException;
import com.itccxx.core.data.db.model.MessageInfo;

public interface MessageService {
	
	/**
	 * ���һ���û�������Ϣ
	 * @param messageInfo ��Ϣʵ����
	 * @return
	 */
	public boolean sendMessage(MessageInfo messageInfo)throws MessageException;
	
	/**
	 * ������Ϣ�е����в�������Ϣ����ѯ��Ϣ��¼������֮��ʹ��And���ӷ�
	 * @param messageInfo ��Ϣʵ���࣬����ֵ������ݸ�ֵ����������ѯ
	 * @param startIndex	��ʼ����
	 * @param pageSize	��ǰҳ��С
	 * @return
	 */
	public MessageInfo[] queryMessage(MessageInfo messageInfo,int startIndex,int pageSize)throws MessageException;
	
	/**
	 * ������Ϣ�еļ�¼���������зǿղ���ͨ�����ΣĵĹ�ϵ����ѯ���������ļ�¼��
	 * @param messageInfo ��Ϣʵ���࣬
	 * @return
	 */
	public long queryMessageCount(MessageInfo messageInfo)throws MessageException;

	
}
