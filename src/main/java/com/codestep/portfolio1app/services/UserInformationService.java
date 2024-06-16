package com.codestep.portfolio1app.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codestep.portfolio1app.entities.Prefecture;
import com.codestep.portfolio1app.entities.User;
import com.codestep.portfolio1app.entities.UserInformation;
import com.codestep.portfolio1app.repositories.UserInformationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserInformationService {
	@Autowired
	UserInformationRepository userInformationRepository;
	
	public List<UserInformation> findAll(){
		return userInformationRepository.findAll();
	}
	
	public void create(User user, String email, Prefecture prefecture) {
		UserInformation userInformation = new UserInformation();
		userInformation.setUser(user);
		userInformation.setEmail(email);
		userInformation.setPrefecture(prefecture);
		userInformation.setCreated(LocalDateTime.now());
		userInformationRepository.saveAndFlush(userInformation);
	}
}
