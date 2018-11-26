package com.dragleo.ms.common.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.dragleo.ms.login.helper.LoginContant;
import com.dragleo.ms.user.model.UserVO;
import com.dragleo.ms.user.service.IUserService;
@Component
public class LoginArgumentResolver implements HandlerMethodArgumentResolver{
	
	@Autowired
	private IUserService userService;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		Class<?> clazz= parameter.getParameterType();
		return clazz== UserVO.class;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request= webRequest.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
		String paramToken = request.getParameter(LoginContant.COOKI_NAME_TOKEN);
		String cookieToken =getCookieToken(request);	
		if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
			return null;
		}
		String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
		return userService.getLoginByToken(response,token);
	}

	private String getCookieToken(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if(LoginContant.COOKI_NAME_TOKEN.equals(cookie.getName())){
				return cookie.getValue();
			}
		}
		return "";
	}

}
