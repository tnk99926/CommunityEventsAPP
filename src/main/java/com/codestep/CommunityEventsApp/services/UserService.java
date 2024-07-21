package com.codestep.CommunityEventsApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.codestep.CommunityEventsApp.repositories.UserRepository;

@Service
public class UserService {	
	@Autowired
	UserDetailsManager userDetailsManager;
	
	@Autowired
	UserRepository userRepository;
	
	public com.codestep.CommunityEventsApp.entities.User getByUsername(String username){
		return userRepository.getByUsername(username);
	}
	
	public void create(com.codestep.CommunityEventsApp.entities.User formUser) {
		var user = User
				.withUsername(formUser.getUsername())
				.password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(formUser.getPassword()))
				.roles("USER")
				.build();	
		userDetailsManager.createUser(user);
	}
}
