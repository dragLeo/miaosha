package com.dragleo.ms.common.result;

public class AjaxResult<T> {

	private int code;
	private String msg;
	private T data;
	
	
	
	
	private AjaxResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	private AjaxResult(T data) {
		super();
		this.data = data;
	}
	
	private AjaxResult(CodeMsg codeMsg) {
		if(codeMsg != null) {
			this.code = codeMsg.getCode();
			this.msg = codeMsg.getMsg();
		}
	}
	
	/**
	 * 成功时返回
	 * @param data
	 * @return
	 */
	public static <T>AjaxResult<T> success(T data){
		return new AjaxResult<T>(data);
	}
	
	/**
	 * 失败时返回
	 * @param data
	 * @return
	 */
	public static <T>AjaxResult<T> error(T data){
		return new AjaxResult<T>(data);
	}
	
	public static  <T> AjaxResult<T> error(CodeMsg codeMsg){
		return new AjaxResult<T>(codeMsg);
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
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
	
}
