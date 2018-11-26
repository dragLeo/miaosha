package com.dragleo.ms.user.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dragleo.ms.common.UUIDUtils;
import com.dragleo.ms.common.md5.MD5Utils;
import com.dragleo.ms.login.helper.LoginContant;
import com.dragleo.ms.login.helper.LoginKey;
import com.dragleo.ms.login.model.LoginVO;
import com.dragleo.ms.redis.RedisHelper;
import com.dragleo.ms.user.dao.IUserDao;
import com.dragleo.ms.user.model.UserVO;
import com.dragleo.ms.user.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	private final static Logger log= LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private IUserDao userDao;
	@Autowired
	private RedisHelper redisHelper;
	
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
	public boolean login(HttpServletResponse response,LoginVO loginVo) throws Exception {
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
		UserVO vo = userDao.login(loginVo);
		if(null != vo){
			String token = UUIDUtils.genUUID();
			addCookie(response, token, vo);
			return true;
		}
		return false;
	}


	@Override
	public UserVO getLoginByToken(HttpServletResponse response,String token) {
		UserVO vo = redisHelper.getKey(LoginKey.token, token, UserVO.class);
		if(null !=vo){
			addCookie(response, token, vo);
			return vo;
		}
		return null;
	}


	/**
	 * update cookie expire time
	 * @param response
	 * @param token
	 * @param vo
	 */
	private void addCookie(HttpServletResponse response, String token, UserVO vo) {
		redisHelper.setKey(LoginKey.token, token, vo);
		Cookie cookie = new Cookie(LoginContant.COOKI_NAME_TOKEN,token );
		cookie.setMaxAge(LoginKey.token.expireTime());
		cookie.setPath("/");
		response.addCookie(cookie);
	}

}
