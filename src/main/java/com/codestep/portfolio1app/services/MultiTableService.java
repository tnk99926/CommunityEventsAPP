package com.codestep.portfolio1app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codestep.portfolio1app.entities.ActivityPrefecture;
import com.codestep.portfolio1app.entities.UserInformation;
import com.codestep.portfolio1app.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class MultiTableService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserInformationService userInformationService;
	
	@Autowired
	ActivityPrefectureService activityPrefectureService;
	
	public void CreateCompleteUser(com.codestep.portfolio1app.entities.User formUser, UserInformation userInformation, List<ActivityPrefecture> activityPrefectures,BindingResult result) {

	//ユーザ登録
		userService.create(formUser);
			
	//ユーザ情報登録
		com.codestep.portfolio1app.entities.User perent = userRepository.getByUsername(formUser.getUsername());
		userInformationService.create(perent, userInformation);
		
	//活動地域登録		
		if(activityPrefectures != null) {
			activityPrefectureService.create(perent, activityPrefectures,result);
		}
			
	}
	
}
