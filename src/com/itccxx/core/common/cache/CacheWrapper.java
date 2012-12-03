package com.itccxx.core.common.cache;

import java.io.Serializable;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.apache.jcs.engine.CacheElement;

/**
 * ���洦���࣬��ӡ�ɾ������ѯ�������
 * @author CrossCX
 *
 */
public class CacheWrapper {
	
	/**
	 * ����ʵ��
	 */
	public JCS cache = null;
	
	public CacheWrapper(JCS cache){
		this.cache = cache;
	}
	
	/**
	 * ��Ӽ�Ϊkey,ֵΪvalue(Object)�Ķ���
	 * @param key
	 * @param value
	 */
	public void put(String key,Object value){
		try{
			cache.put(key, value);
		}catch(CacheException e){
			
		}
		
	}
	
	/**
	 * ��Ӽ�Ϊkey,ֵΪvalue(Serializable)�Ķ���
	 * @param key
	 * @param value
	 */
	public void put(String key,Serializable value){
		try{
			cache.put(key, value);
		}catch(CacheException e){
			
		}
	}
	
	/**
	 * ��ȡ��Ϊkey��Serialize����
	 * @param key
	 * @return
	 */
	public Serializable get(String key){
		CacheElement cacheElement = (CacheElement) cache.getCacheElement(key);
		if(cacheElement != null){
			Serializable serializable = cacheElement.val;
			return serializable;
		}
		return null;
	}
	
	/**
	 * ��ȡ��Ϊkey��Object����
	 * @param key
	 * @return
	 */
	public Object getObject(String key){
		CacheElement cacheElement = (CacheElement) cache.getCacheElement(key);
		if(cacheElement != null){
			Object objValue = cacheElement.val;
			return objValue;
		}
		return null;
	}
	
	/**
	 * �Ƴ���Ϊkey�Ļ���Ԫ��
	 * @param key
	 * @return
	 */
	public boolean remove(String key){
		try{
			cache.remove(key);
			return true;
		}catch(CacheException e){
			return false;
		}
	}
	
	
	

}
