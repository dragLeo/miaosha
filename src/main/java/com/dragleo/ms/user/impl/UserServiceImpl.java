package com.dragleo.ms.user.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dragleo.ms.common.md5.MD5Utils;
import com.dragleo.ms.login.model.LoginVO;
import com.dragleo.ms.user.dao.IUserDao;
import com.dragleo.ms.user.domain.UserVO;
import com.dragleo.ms.user.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	private final static Logger log= LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private IUserDao userDao;
	
	@Transactional
	@Override
	public UserVO loadById(int id) {
		return userDao.load(id);
	}


	@Transactional(readOnly=false)
	@Override
	public void insert(UserVO userVO) {

		userDao.insertVO(userVO);
	}

	@Override
	public boolean login(LoginVO loginVo) throws Exception {
		String mobile =loginVo.getMobile();
		String password =loginVo.getPassword();
		if(StringUtils.isEmpty(mobile)){
			log.error("手机号为空");;
			throw new Exception("手机号为空");
		}
		if(StringUtils.isEmpty(password)){
			log.error("密码为空");
			throw new Exception("密码为空");
		}
//		String md5Salt = MD5Utils.md5Salt(password);
//		loginVo.setPassword(md5Salt);
		LoginVO vo = userDao.login(loginVo);
		if(null != vo)
			return true;
		return false;
	}

}
