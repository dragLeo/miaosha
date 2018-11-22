package com.dragleo.ms.common.md5;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {

	private static final String salt="1q2w3e4r";
	
	/**
	 * md5 加密
	 * @param str
	 * @return
	 */
	public static String md5(String str){
		return DigestUtils.md5Hex(str);
	}
	
	/**
	 * 加盐
	 * @param str
	 * @return
	 */
	public static String md5Salt(String str){
		String s=str+salt;
		return DigestUtils.md5Hex(s);
	}
	
	public static void main(String[] args) {
		String str="123456";
		String md5 = md5Salt(str);
		System.out.println(md5);
	}
}
