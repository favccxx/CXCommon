package com.itccxx.core;

public class TestReplace {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "{\"aliases\": null,\"displayName\": \"��¼����\",},{\"aliases\": null,\"displayName\": \"��¼��sdf��\",}";
		str=str.replaceAll("null","\"\"");
		str.split("},");
		System.out.println(str);
	}

}
