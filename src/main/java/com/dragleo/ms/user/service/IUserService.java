package com.dragleo.ms.user.service;

import com.dragleo.ms.user.domain.UserVO;

public interface IUserService {

	public UserVO loadById(int id);
	
	public boolean namepwdMatch(String username,String password);
	
	public void insert(UserVO userVO);
}
