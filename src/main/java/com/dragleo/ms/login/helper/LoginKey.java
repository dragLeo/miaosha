package com.dragleo.ms.login.helper;

import com.dragleo.ms.common.keyprefix.BasePrefix;

public class LoginKey extends BasePrefix{


	final static int TOKEN_EXPIRE=24*3600*2;
	
	public LoginKey(int expireTime, String prefix) {
		super(expireTime, prefix);
	}
	
	public static LoginKey token=new LoginKey(TOKEN_EXPIRE,"token"); 

}
