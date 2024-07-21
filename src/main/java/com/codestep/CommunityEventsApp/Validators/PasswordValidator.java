package com.codestep.CommunityEventsApp.Validators;

import com.codestep.CommunityEventsApp.Annotations.Password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String>{
	@Override
	public void initialize(Password password) {
	}
	
	@Override
	public boolean isValid(String input, ConstraintValidatorContext cxt) {
		if(input == null) {
			return true;
		}
		return input.matches("[a-zA-Z0-9]*");
	}
}
