package com.itccxx.core.common.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;

/**
 * ���湤����ʹ��hashMap���û��������
 * @author CrossCX
 *
 */
public class CacheFactory {
	
	private static Map<String, CacheWrapper> cacheMap = new HashMap<String,CacheWrapper>();
	
	/**
	 * ����һ����ΪcacheName�Ļ������
	 * @param cacheName �������������Ϣ
	 * @return
	 */
	private static CacheWrapper createCache(String cacheName){
		JCS cache = null;
		try{
			cache = JCS.getInstance(cacheName);
			return new CacheWrapper(cache);
		}catch(CacheException e){
			return null;
		}
	}
	
	/**
	 * ��ȡһ������ΪcacheName�Ķ�����������ڣ�����null
	 * @param cacheName �������������Ϣ
	 * @return
	 */
	public static CacheWrapper getCacheWrapper(String cacheName){
		return cacheMap.get(cacheName);
	}
	
	/**
	 * ������еĻ���������ݣ���������������
	 */
	public static void clear(){
		try{
			Object[] cacheObjects = cacheMap.keySet().toArray();
			for(int i=0,l=cacheObjects.length;i<l;i++){
				String key = cacheObjects[i].toString();
				CacheWrapper cacheWrapper = cacheMap.get(key);
				cacheWrapper.cache.clear();
			}
		}catch(CacheException e){
			
		}
		
	}
	
	/**
	 * ������ʼ���Ļ������
	 */
	public static void init(){
		cacheMap.put(CacheObjectConstants.BUSINESS_MODULE_CACHE, createCache(CacheObjectConstants.BUSINESS_MODULE_CACHE));
		cacheMap.put(CacheObjectConstants.DATA_DICTIONARY_CACHE, createCache(CacheObjectConstants.DATA_DICTIONARY_CACHE));
		cacheMap.put(CacheObjectConstants.SYSTEM_CONFIG_CACHE, createCache(CacheObjectConstants.SYSTEM_CONFIG_CACHE));
	}

}
