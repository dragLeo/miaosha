package com.dragleo.ms.common.result;

public class CodeMsg {

	private int code;
	private String msg;
	
	
	public static CodeMsg SUCCESS=new CodeMsg(0, "success");
	public static CodeMsg SERVER_ERROR=new CodeMsg(500100, "服务端异常");
	public static CodeMsg BIND_ERROR=new CodeMsg(500101, "参数绑定异常");
	
	
	
	public CodeMsg(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "CodeMsg [code=" + code + ", msg=" + msg + "]";
	}
	
	
	
}
