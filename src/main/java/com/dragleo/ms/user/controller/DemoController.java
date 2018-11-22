package com.dragleo.ms.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dragleo.ms.common.result.AjaxResult;
import com.dragleo.ms.redis.RedisHelper;
import com.dragleo.ms.user.domain.UserVO;
import com.dragleo.ms.user.helper.UserKey;
import com.dragleo.ms.user.service.IUserService;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@Autowired 
	private IUserService userService;
	@Autowired
	private RedisHelper helper;
	
	@RequestMapping("/hello")
    @ResponseBody
    public AjaxResult<UserVO> home() {
        return AjaxResult.success(userService.loadById(1));
    }
	
	@RequestMapping("/getUser")
    @ResponseBody
	public AjaxResult<UserVO> getId(){
		UserVO user = helper.getKey(UserKey.getById, "1", UserVO.class);
		if(null ==user){
			System.out.println("----------------------------");
			System.out.println("----------------------------");
			System.out.println("redis mei you key");
			user = userService.loadById(1);
		}
			
		return AjaxResult.success(user);
	}
	@RequestMapping("/setUKey")
	@ResponseBody
	public AjaxResult<UserVO> setUKey(){
		UserVO user = userService.loadById(1);
		helper.setKey(UserKey.getById, "1", user);
		return AjaxResult.success(user);
	}
}
