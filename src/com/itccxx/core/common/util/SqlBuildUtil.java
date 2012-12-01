package com.itccxx.core.common.util;

import com.itccxx.core.common.constants.OperateType;
import com.itccxx.core.common.exception.InvalidParameter;

/**
 * ����sql�е�where��ѯ������ԭ�����ǽ��÷��������ر�ϸ�£����ǿ��Ǹ���ʹ��Ƶ�ʽϸߣ���������ػή��ϵͳ�����ܡ�
 *  * @author CrossCX
 *
 */
public class SqlBuildUtil {
	
	/**
	 * ������ѯ�ַ������͵ĵ�ֵ��ѯ����
	 * @param whereClause ԭ�е�where����
	 * @param relationType �������ͣ�����RelationType�еĳ���
	 * @param propertyName ��Ҫ��ѯ�������ֶ�
	 * @param operateType �������ͣ�����OperateType�еĳ���
	 * @param propertyValue ��ѯ�Ĳ���ֵ
	 * @param hasLeftBrackets �Ƿ��������� 
	 * @param hasRightBrackets �Ƿ���������
	 * @return
	 */
	public static String addCondition(String whereClause,String relationType,String propertyName,String operateType,String propertyValue,boolean hasLeftBrackets,boolean hasRightBrackets){
		if(whereClause == null || whereClause.trim().length() == 0){
			whereClause = "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(whereClause).append(relationType);
		if(hasLeftBrackets){
			sb.append(" ( ");
		}
		sb.append(propertyName).append(" ").append(operateType).append(" '");
		if(OperateType.LIKE.equals(operateType)){
			propertyValue = "%" + propertyValue + "%";
		}
		sb.append(propertyValue).append("' ");
		if(hasRightBrackets){
			sb.append(")");
		}
		return sb.toString();
	}
	
//	public static String addCondition(String whereClause,String relationType,String propertyName,String operateType,int propertyValue,boolean hasLeftBrackets,boolean hasRightBrackets){		
//		return addCondition(whereClause,relationType,propertyName,operateType,propertyValue,hasLeftBrackets,hasRightBrackets);
//	}
	
	/**
	 * ������ѯ��ʼ��������ֹ���ڵķ�Χ�ڲ�ѯ����
	 * @param whereClause ԭ�е�where����
	 * @param propertyName ��Ҫ��ѯ�������ֶ�
	 * @param startDate	��ʼ����
	 * @param endDate ��ֹ����
	 * @return
	 * @throws InvalidParameter
	 */
	public static String addDateTimeCondition(String whereClause,String propertyName,String startDate,String endDate) throws InvalidParameter{
		if(Utils.isEmpty(propertyName)){
			throw new InvalidParameter("��Ч�Ĳ�����" + propertyName);
		}
		StringBuffer sb = new StringBuffer();
		if(Utils.isEmpty(whereClause)){
			sb.append(whereClause);
		}
		if(!Utils.isEmpty(startDate)){
			sb.append(" and ")
			.append(propertyName)
			.append(" >= ")
			.append(startDate);
		}
		if(!Utils.isEmpty(endDate)){
			sb.append(" and ")
			.append(propertyName)
			.append(" <= ")
			.append(endDate);
		}
		return sb.toString();
	}
	
	/**
	 * �������ٲ�ѯ�ַ�����And����
	 * @param whereClause ԭ�е�where����
	 * @param propertyName ��Ҫ��ѯ����������
	 * @param propertyValue ����ֵ
	 * @return
	 */
	public static String addSingleAndCondition(String whereClause,String propertyName,String propertyValue){
		StringBuffer sb = new StringBuffer();
		if(!Utils.isEmpty(whereClause)){
			sb.append(whereClause);
		}
		sb.append(" and ").append(propertyName).append(" = '").append(propertyValue).append("'");
		return sb.toString();
	}
	
	/**
	 * ������ֵor��ѯ����
	 * @param whereClause ԭ�е�where����
	 * @param relationType ��ϵ�������and��or��
	 * @param propertyName ��������
	 * @param operateType �������������OperateType��ȡֵ��= <> like�ȣ�
	 * @param propertyValue ����ֵ����ֵ֮����÷ָ������зָ
	 * @param separator �ָ���,����ֵ�Ļ���Ĭ��Ϊ","
	 * @return
	 */
	public static String addMulOrCondition(String whereClause,String relationType,String propertyName,String operateType,String propertyValue,String separator){
		StringBuffer sb = new StringBuffer();
		if(!Utils.isEmpty(whereClause)){
			sb.append(whereClause);
		}
		if(Utils.isEmpty(separator)){
			separator = ",";
		}
		sb.append(relationType).append(" (");
		String mulValues[] = propertyValue.split(separator);
		int i=0;
		for(String singleValue : mulValues){
			sb.append(propertyName).append(" ").append(operateType).append(" '");
			if(OperateType.LIKE.equals(operateType)){
				singleValue = "%" + singleValue + "%";
			}
			sb.append(singleValue).append(" '");
			i++;
			if(i+1 < mulValues.length){
				sb.append(" or ");
			}
		}
		sb.append(")");
		return sb.toString();
	}
	
	
	

}
