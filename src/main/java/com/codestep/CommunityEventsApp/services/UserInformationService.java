package com.codestep.CommunityEventsApp.services;

import java.time.LocalDateTime;
import java.util.List;

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
	
	public List<UserInformation> findAll(){
		return userInformationRepository.findAll();
	}
	
	public void create(User user, UserInformation userInformation) {
		userInformation.setUser(user);
		userInformation.setPrefecture(prefectureService.getById(userInformation.getPrefectureId()));
		userInformation.setCreated(LocalDateTime.now());
		userInformationRepository.saveAndFlush(userInformation);
	}
}
