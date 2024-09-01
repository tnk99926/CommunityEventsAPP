package com.codestep.CommunityEventsApp.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codestep.CommunityEventsApp.entities.User;
import com.codestep.CommunityEventsApp.entities.UserInformation;
import com.codestep.CommunityEventsApp.repositories.UserInformationRepository;

@Service
public class UserInformationService {
	@Autowired
	UserInformationRepository userInformationRepository;
	
	@Autowired
	private PrefectureService prefectureService;
	
	public UserInformation getByUsername(String username){
		return userInformationRepository.getByUsername(username);
	}
	
	public void create(User user, UserInformation userInformation) {
		userInformation.setUser(user);
		userInformation.setPrefecture(prefectureService.getById(userInformation.getPrefectureId()));
		userInformation.setCreated(LocalDateTime.now());
		userInformationRepository.saveAndFlush(userInformation);
	}
	
	public void update(User user, UserInformation userInformation) {
		UserInformation updatedUserInformation = userInformationRepository.getByUsername(user.getUsername());
		updatedUserInformation.setPrefectureId(userInformation.getPrefectureId());
		updatedUserInformation.setPrefecture(prefectureService.getById(userInformation.getPrefectureId()));
		updatedUserInformation.setEmail(userInformation.getEmail());
		updatedUserInformation.setUpdated(LocalDateTime.now());
		userInformationRepository.saveAndFlush(updatedUserInformation);
	}
}
