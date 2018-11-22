package com.dragleo.ms.user.service;

import com.dragleo.ms.login.model.LoginVO;
import com.dragleo.ms.user.domain.UserVO;

public interface IUserService {

	public UserVO loadById(int id);
	
	public void insert(UserVO userVO);

	public boolean login(LoginVO loginVo) throws Exception;
}
