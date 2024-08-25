package com.codestep.CommunityEventsApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codestep.CommunityEventsApp.Exceptions.ValidationException;
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
	
	public void create(com.codestep.CommunityEventsApp.entities.User formUser,BindingResult result) {
		if (!userDetailsManager.userExists(formUser.getUsername())) {
            throw new ValidationException(result);
        }
		
		var user = User
				.withUsername(formUser.getUsername())
				.password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(formUser.getPassword()))
				.roles("USER")
				.build();	
		userDetailsManager.createUser(user);
		
	}
	
	//将来のユーザー名変更用メソッド
	public void updateName(com.codestep.CommunityEventsApp.entities.User currentUser, com.codestep.CommunityEventsApp.entities.User updatedUser) {
		UserDetails updateUser = User.withUsername(updatedUser.getUsername())
									.password(currentUser.getPassword())
									.roles("USER")
									.build();	
		userDetailsManager.createUser(updateUser);
	}
	
}
