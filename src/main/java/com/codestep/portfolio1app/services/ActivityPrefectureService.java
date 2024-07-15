package com.codestep.portfolio1app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.codestep.portfolio1app.Exceptions.ValidationException;
import com.codestep.portfolio1app.entities.ActivityPrefecture;
import com.codestep.portfolio1app.entities.User;
import com.codestep.portfolio1app.repositories.ActivityPrefectureRepository;
import com.codestep.portfolio1app.repositories.PrefectureRepository;


@Service
public class ActivityPrefectureService {
	
	@Autowired
	ActivityPrefectureRepository activityPrefectureRepository;
	
	@Autowired
	PrefectureRepository prefectureRepository;
	
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
			activityPrefecture.setPrefecture(prefectureRepository.getReferenceById(activityPrefecture.getPrefectureId()));
			activityPrefectureRepository.saveAndFlush(activityPrefecture);
		}
		
		
	}
}
