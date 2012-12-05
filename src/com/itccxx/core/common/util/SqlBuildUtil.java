package com.itccxx.core.common.util;

import com.itccxx.core.common.constants.OperateType;
import com.itccxx.core.common.exception.InvalidParameter;

/**
 * 构建sql中的where查询条件，原本想是将该方法抽象到特别细致，但是考虑该类使用频率较高，过多的重载会降低系统的性能。
 *  * @author CrossCX
 *
 */
public class SqlBuildUtil {
	
	/**
	 * 根据原有的SQL构建分页条件(如使用Oracle数据库，应该原sql语句指定指定唯一的排序条件)
	 * @param sql sql查询条件
	 * @param startIndex 当前索引
	 * @param pageSize 每页大小
	 * @return
	 */
	public static String buildPagingSQL(String sql,int startIndex,int pageSize){
		startIndex = startIndex > 0 ? startIndex : 1;
		//如果需要查询的结果数量是大于0的，则装配成分页形式，否则，不分页，全部查询
		if(pageSize > 0){
			sql = "SELECT * FROM (SELECT T1.*, ROWNUM RN FROM (" + sql + ") T1 WHERE ROWNUM < " + (pageSize + startIndex) + ") T WHERE T.RN >= " + startIndex;
		}
		return sql;
	}
	
	/**
	 * 构建查询字符串类型的单值查询条件
	 * @param whereClause 原有的where条件
	 * @param relationType 关联类型，来自RelationType中的常量
	 * @param propertyName 需要查询的属性字段
	 * @param operateType 操作类型，来自OperateType中的常量
	 * @param propertyValue 查询的参数值
	 * @param hasLeftBrackets 是否有左括号 
	 * @param hasRightBrackets 是否有右括号
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
	 * 构建查询起始日期与终止日期的范围内查询条件
	 * @param whereClause 原有的where条件
	 * @param propertyName 需要查询的日期字段
	 * @param startDate	起始日期
	 * @param endDate 终止日期
	 * @return
	 * @throws InvalidParameter
	 */
	public static String addDateTimeCondition(String whereClause,String propertyName,String startDate,String endDate) throws InvalidParameter{
		if(StringHelper.isEmpty(propertyName)){
			throw new InvalidParameter("无效的参数：" + propertyName);
		}
		StringBuffer sb = new StringBuffer();
		if(StringHelper.isEmpty(whereClause)){
			sb.append(whereClause);
		}
		if(!StringHelper.isEmpty(startDate)){
			sb.append(" and ")
			.append(propertyName)
			.append(" >= ")
			.append(startDate);
		}
		if(!StringHelper.isEmpty(endDate)){
			sb.append(" and ")
			.append(propertyName)
			.append(" <= ")
			.append(endDate);
		}
		return sb.toString();
	}
	
	/**
	 * 构建快速查询字符串的And条件
	 * @param whereClause 原有的where条件
	 * @param propertyName 需要查询的属性名称
	 * @param propertyValue 属性值
	 * @return
	 */
	public static String addSingleAndCondition(String whereClause,String propertyName,String propertyValue){
		StringBuffer sb = new StringBuffer();
		if(!StringHelper.isEmpty(whereClause)){
			sb.append(whereClause);
		}
		sb.append(" and ").append(propertyName).append(" = '").append(propertyValue).append("'");
		return sb.toString();
	}
	
	/**
	 * 构建多值or查询条件
	 * @param whereClause 原有的where条件
	 * @param relationType 关系运算符（and、or）
	 * @param propertyName 属性名称
	 * @param operateType 操作运算符（从OperateType中取值：= <> like等）
	 * @param propertyValue 属性值（多值之间采用分隔符进行分割）
	 * @param separator 分隔符,传空值的话，默认为","
	 * @return
	 */
	public static String addMulOrCondition(String whereClause,String relationType,String propertyName,String operateType,String propertyValue,String separator){
		StringBuffer sb = new StringBuffer();
		if(!StringHelper.isEmpty(whereClause)){
			sb.append(whereClause);
		}
		if(StringHelper.isEmpty(separator)){
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
