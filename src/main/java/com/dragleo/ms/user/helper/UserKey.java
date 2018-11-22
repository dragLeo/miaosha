package com.dragleo.ms.user.helper;

import com.dragleo.ms.common.keyprefix.BasePrefix;

public class UserKey extends BasePrefix{

	public UserKey(String prefix) {
		super(prefix);
	}
	
	public static UserKey getById= new UserKey("id");
	public static UserKey getByName =new UserKey("name");

}
