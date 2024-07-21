package com.codestep.CommunityEventsApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.codestep.CommunityEventsApp.Exceptions.ValidationException;
import com.codestep.CommunityEventsApp.entities.ActivityPrefecture;
import com.codestep.CommunityEventsApp.entities.User;
import com.codestep.CommunityEventsApp.repositories.ActivityPrefectureRepository;


@Service
public class ActivityPrefectureService {
	
	@Autowired
	ActivityPrefectureRepository activityPrefectureRepository;
	
	@Autowired
	PrefectureService prefectureService;
	
	@Autowired
    private Validator validator;
	
	public List<ActivityPrefecture> getByUser(User user) {
		return activityPrefectureRepository.findByUser(user);
	}
	public void create(User user, List<ActivityPrefecture> activityPrefectures,BindingResult result) {
		
		for (ActivityPrefecture activityPrefecture : activityPrefectures) {
			validator.validate(activityPrefecture, result);
		}
		if (result.hasErrors()) {
            throw new ValidationException(result);
        }
		
		for(ActivityPrefecture activityPrefecture: activityPrefectures) {
			activityPrefecture.setUser(user);
			activityPrefecture.setPrefecture(prefectureService.getById(activityPrefecture.getPrefectureId()));
			activityPrefectureRepository.saveAndFlush(activityPrefecture);
		}
		
		
	}
}
