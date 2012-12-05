package com.itccxx.core.common.util;

public class StringHelper {
	
	public static boolean isEmpty(String str){
		if(str == null || str.trim().length() == 0){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotEmpty(String str){
		if(isEmpty(str)){
			return false;
		}else{
			return true;
		}
	}
	
	public static boolean isNotNull(Object obj){
		return obj != null;
	}

}
