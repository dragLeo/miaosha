package com.dragleo.ms.user.service;

import javax.servlet.http.HttpServletResponse;

import com.dragleo.ms.login.model.LoginVO;
import com.dragleo.ms.user.domain.UserVO;

public interface IUserService {

	public UserVO loadById(int id);
	
	public void insert(UserVO userVO);

	public boolean login(HttpServletResponse response, LoginVO loginVo) throws Exception;

	/**
	 * get loginVO by token
	 * @param response 
	 * @param token
	 * @return
	 */
	public LoginVO getLoginByToken(HttpServletResponse response, String token);
}
