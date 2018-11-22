package com.dragleo.ms.common.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
/**
 * 校验类
 * @author g
 *
 */
public class ValidatorUtil {

	
	private static final Pattern mobile_patten = Pattern.compile("1\\d{10}");
	/**
	 * 校验ismoble 注解
	 * @param moble
	 * @return
	 */
	public static boolean isMoble(String moble){
		if(StringUtils.isEmpty(moble)){
			return false;
		}
		Matcher m =mobile_patten.matcher(moble);
		return m.matches();
	}
}
