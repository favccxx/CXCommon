package com.itccxx.core.common.cache;

import java.io.Serializable;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.apache.jcs.engine.CacheElement;

/**
 * 缓存处理类，添加、删除、查询缓存对象
 * @author CrossCX
 *
 */
public class CacheWrapper {
	
	/**
	 * 缓存实例
	 */
	public JCS cache = null;
	
	public CacheWrapper(JCS cache){
		this.cache = cache;
	}
	
	/**
	 * 添加键为key,值为value(Object)的对象
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
	 * 添加键为key,值为value(Serializable)的对象
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
	 * 获取键为key的Serialize对象
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
	 * 获取键为key的Object对象
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
	 * 移除键为key的缓存元素
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
