package com.itccxx.core.common.util;

public class Utils {
	
	public static boolean isEmpty(String str){
		if(str == null || str.trim().length() == 0){
			return true;
		}else{
			return false;
		}
	}

}
