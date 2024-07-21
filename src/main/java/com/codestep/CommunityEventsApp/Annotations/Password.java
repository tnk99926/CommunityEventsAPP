package com.codestep.CommunityEventsApp.Annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.codestep.CommunityEventsApp.Validators.PasswordValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface Password {
	String message() default "パスワードは半角英数字で入力してください";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};
}
