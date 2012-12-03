package com.itccxx.core.common.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;

/**
 * 缓存工厂，使用hashMap配置缓存的内容
 * @author CrossCX
 *
 */
public class CacheFactory {
	
	private static Map<String, CacheWrapper> cacheMap = new HashMap<String,CacheWrapper>();
	
	/**
	 * 创建一个键为cacheName的缓存对象
	 * @param cacheName 缓存对象配置信息
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
	 * 获取一个名称为cacheName的对象，如果不存在，返回null
	 * @param cacheName 缓存对象配置信息
	 * @return
	 */
	public static CacheWrapper getCacheWrapper(String cacheName){
		return cacheMap.get(cacheName);
	}
	
	/**
	 * 清除所有的缓存对象内容，但不清除缓存对象
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
	 * 创建初始化的缓存对象
	 */
	public static void init(){
		cacheMap.put(CacheObjectConstants.BUSINESS_MODULE_CACHE, createCache(CacheObjectConstants.BUSINESS_MODULE_CACHE));
		cacheMap.put(CacheObjectConstants.DATA_DICTIONARY_CACHE, createCache(CacheObjectConstants.DATA_DICTIONARY_CACHE));
		cacheMap.put(CacheObjectConstants.SYSTEM_CONFIG_CACHE, createCache(CacheObjectConstants.SYSTEM_CONFIG_CACHE));
	}

}
