package com.dragleo.ms.common.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.dragleo.ms.common.anno.IsMobile;


public class IsMobileValidator implements ConstraintValidator<IsMobile, String>{

	private boolean required = false;
	
	@Override
	public void initialize(IsMobile constraintAnnotation) {
		required=constraintAnnotation.required();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(required){
			return ValidatorUtil.isMoble(value);
		}else {
			if(StringUtils.isEmpty(value)){
				return true;
			}else{
				return ValidatorUtil.isMoble(value);
			}
		}
	}

}
