package com.dragleo.ms.common;

import java.util.UUID;

public class UUIDUtils {

	public static String genUUID(){
		String uuid=UUID.randomUUID().toString();
		return uuid.replace("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(genUUID());
	}
}
