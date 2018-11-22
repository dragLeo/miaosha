package com.dragleo.ms.common.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.dragleo.ms.common.validate.IsMobileValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy={IsMobileValidator.class})
public @interface IsMobile {

	boolean required() default true;
	String message() default "手机格式错误";
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
