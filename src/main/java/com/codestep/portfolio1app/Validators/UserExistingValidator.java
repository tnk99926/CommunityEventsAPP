package com.codestep.portfolio1app.Validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;

import com.codestep.portfolio1app.Annotations.UserExisting;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserExistingValidator implements ConstraintValidator<UserExisting, String>{
	@Autowired
	UserDetailsManager userDetailsManager;
	
	@Override
	public void initialize(UserExisting userExisting) {
	}
	
	@Override
	public boolean isValid(String input, ConstraintValidatorContext cxt) {
		if(input == null) {
			return true;
		}
		return !userDetailsManager.userExists(input);
	}
}
