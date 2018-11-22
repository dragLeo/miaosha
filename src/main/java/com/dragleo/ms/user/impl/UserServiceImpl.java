package com.dragleo.ms.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dragleo.ms.user.dao.IUserDao;
import com.dragleo.ms.user.domain.UserVO;
import com.dragleo.ms.user.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDao userDao;
	
	@Transactional
	@Override
	public UserVO loadById(int id) {
		return userDao.load(id);
	}

	@Transactional
	@Override
	public boolean namepwdMatch(String username, String password) {
		UserVO user= userDao.findUserByUsernameAndPwd(username,password);
		if(user !=null)
			return true;
		return false;
	}

	@Transactional(readOnly=false)
	@Override
	public void insert(UserVO userVO) {

		userDao.insertVO(userVO);
	}

}
