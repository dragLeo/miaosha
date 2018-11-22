package com.dragleo.ms.login.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.dragleo.ms.common.anno.IsMobile;


public class LoginVO {

	@NotNull
	@IsMobile
	private String mobile;
	
	@NotNull
	@Length(min=32)
	private String password;


	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginVO [moble=" + mobile + ", password=" + password + "]";
	}
	
	
}
