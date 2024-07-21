package com.codestep.CommunityEventsApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codestep.CommunityEventsApp.entities.ActivityPrefecture;
import com.codestep.CommunityEventsApp.entities.UserInformation;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class MultiTableService {
	@Autowired
	UserService userService;
	
	@Autowired
	UserInformationService userInformationService;
	
	@Autowired
	ActivityPrefectureService activityPrefectureService;
	
	public void CreateCompleteUser(com.codestep.CommunityEventsApp.entities.User formUser, UserInformation userInformation, List<ActivityPrefecture> activityPrefectures,BindingResult result) {

	//ユーザ登録
		userService.create(formUser);
			
	//ユーザ情報登録
		com.codestep.CommunityEventsApp.entities.User perent = userService.getByUsername(formUser.getUsername());
		userInformationService.create(perent, userInformation);
		
	//活動地域登録		
		if(activityPrefectures != null) {
			activityPrefectureService.create(perent, activityPrefectures,result);
		}
			
	}
	
}
