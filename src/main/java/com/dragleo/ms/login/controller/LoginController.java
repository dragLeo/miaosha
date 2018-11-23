package com.dragleo.ms.login.controller;

import java.awt.Toolkit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.transform.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dragleo.ms.common.result.AjaxResult;
import com.dragleo.ms.login.model.LoginVO;
import com.dragleo.ms.redis.RedisHelper;
import com.dragleo.ms.user.service.IUserService;


@Controller
@RequestMapping("/login")
public class LoginController {
	
	
	@Autowired
	private IUserService userService;
	
	private static Logger log= LoggerFactory.getLogger(LoginController.class);

	@RequestMapping("/askLogin")
    public String askLogin(HttpServletRequest request) {
		
        return "login";
    }
	
	@RequestMapping("/doLogin")
    @ResponseBody
    public AjaxResult<Boolean> doLogin(HttpServletRequest request ,HttpServletResponse response, @Valid LoginVO loginVo) {
    	log.info(loginVo.toString());
    	//登录
    	boolean login=false;
		try {
			login = userService.login(response,loginVo);
			if(login){
				 
				return AjaxResult.success(login);
				
	    	}
			return AjaxResult.error(login);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error(login);
		}
    }
}
