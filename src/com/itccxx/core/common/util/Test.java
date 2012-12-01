package com.itccxx.core.common.util;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Object propertyValue = 75453;
		if(propertyValue instanceof Integer){
			System.out.println(1 + "AAA");
		}
		if(propertyValue instanceof String){
			System.out.println(2 + "AAA");
		}
		if(propertyValue instanceof Long){
			System.out.println(3 + "AAA");
		}
	}

}
