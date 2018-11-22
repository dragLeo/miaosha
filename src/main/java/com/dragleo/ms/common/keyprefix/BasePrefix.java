package com.dragleo.ms.common.keyprefix;

public abstract class BasePrefix implements PrefixKey{

	private int expireTime;
	private String prefix;
	
	
	public BasePrefix(String prefix) {
		super();
		this.expireTime=0;
		this.prefix = prefix;
	}
	public BasePrefix(int expireTime, String prefix) {
		super();
		this.expireTime = expireTime;
		this.prefix = prefix;
	}
	@Override
	public int expireTime() {//0代表 永不过期
		return  expireTime;
	}
	@Override
	public String getPrefix() {
		String className=getClass().getSimpleName();
		return className+":"+prefix;
	}
	
	
	
}
