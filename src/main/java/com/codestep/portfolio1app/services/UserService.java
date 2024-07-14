package com.codestep.portfolio1app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService {	
	@Autowired
	UserDetailsManager userDetailsManager;
	
	public void create(com.codestep.portfolio1app.entities.User formUser) {
			var user = User
					.withUsername(formUser.getUsername())
					.password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(formUser.getPassword()))
					.roles("USER")
					.build();	
			userDetailsManager.createUser(user);
	}
}
