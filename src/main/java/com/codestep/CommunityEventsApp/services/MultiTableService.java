package com.codestep.CommunityEventsApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codestep.CommunityEventsApp.entities.User;
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
	
	@Autowired
	UserDetailsManager userDetailsManager;
	
	public void createCompleteUser(User user, UserInformation userInformation, List<Long> activityPrefectureIds,BindingResult result) {

	//ユーザ登録
		userService.create(user,result);
			
	//ユーザ情報登録
		User parent = userService.getByUsername(user.getUsername());
		userInformationService.create(parent, userInformation);
		
	//活動地域登録		
		if(activityPrefectureIds != null) {
			activityPrefectureService.create(parent, activityPrefectureIds,result);
		}
			
	}
	
	
	public void updateCompleteUser(User user, UserInformation userInformation, List<Long> activityPrefectureIds,BindingResult result) {
			//ユーザ情報更新
			userInformationService.update(user, userInformation);
			//活動地域更新
			if(activityPrefectureIds != null) {
				activityPrefectureService.update(user, activityPrefectureIds,result);
			}
	}
}
