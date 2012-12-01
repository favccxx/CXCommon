package com.itccxx.core.data.db.inf;

import java.util.Date;

import com.itccxx.core.common.exception.AuditException;
import com.itccxx.core.data.db.model.AuditInfo;

/**
 * ��ǰ�����Ϣֻ֧����Ӻ�ɾ��
 * @author CrossCX
 *
 */
public interface AuditService {
	
	/**
	 * ���һ�������Ϣ
	 * @param auditInfo �����Ϣ
	 * @throws AuditException
	 */
	public void addAudit(AuditInfo auditInfo)throws AuditException;
	
	/**
	 * ��ѯ�����Ϣ
	 * @param userId	�û�Id
	 * @param moduleName	ģ������
	 * @param operation	��������
	 * @param startDate	��ʼ����
	 * @param endDate	��ֹ����
	 * @param memo	�������
	 * @param startIndex �Ӷ�������¼��ʼ��ѯ��startIndex<=0 ����Ϊnull���ӵ�һ����ʼ��ѯ
	 * @param count	���β�ѯ��ȡ��¼����������Ϊcount<=0 ����ΪNll,Ĭ�ϲ�ѯ500��
	 * @return
	 * @throws AuditException
	 */
	public AuditInfo[] queryAuditInfoByModule(String userId,String moduleName, String operation, Date startDate,Date endDate,String memo,int startIndex,int count)throws AuditException;
	
	
	
}
