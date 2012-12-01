package com.itccxx.core;

public class TestReplace {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "{\"aliases\": null,\"displayName\": \"记录报告\",},{\"aliases\": null,\"displayName\": \"记录报sdf告\",}";
		str=str.replaceAll("null","\"\"");
		str.split("},");
		System.out.println(str);
	}

}
