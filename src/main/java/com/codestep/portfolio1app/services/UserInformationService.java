package com.codestep.portfolio1app.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codestep.portfolio1app.entities.User;
import com.codestep.portfolio1app.entities.UserInformation;
import com.codestep.portfolio1app.repositories.PrefectureRepository;
import com.codestep.portfolio1app.repositories.UserInformationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserInformationService {
	@Autowired
	UserInformationRepository userInformationRepository;
	
	@Autowired
	private PrefectureRepository prefectureRepository;
	
	public List<UserInformation> findAll(){
		return userInformationRepository.findAll();
	}
	
	public void create(User user, UserInformation userInformation) {
		userInformation.setUser(user);
		userInformation.setPrefecture(prefectureRepository.getReferenceById(userInformation.getPrefectureId()));
		userInformation.setCreated(LocalDateTime.now());
		userInformationRepository.saveAndFlush(userInformation);
	}
}
