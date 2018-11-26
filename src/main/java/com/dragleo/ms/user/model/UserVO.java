package com.dragleo.ms.user.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.dragleo.ms.common.anno.IsMobile;

public class UserVO {
	private long id;
	private String username;
	@NotNull
	@Length(min=32)
	private String password;
	private String account ;
	@NotNull
	@IsMobile
	private String mobile;
	private String tel;
	private String email;
	private int isloacked ;//0未锁定  1锁定，需解锁
	private String url ;//头像
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIsloacked() {
		return isloacked;
	}
	public void setIsloacked(int isloacked) {
		this.isloacked = isloacked;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
