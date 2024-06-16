package com.codestep.portfolio1app.services;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codestep.portfolio1app.entities.ActivityPrefecture;
import com.codestep.portfolio1app.entities.User;
import com.codestep.portfolio1app.repositories.ActivityPrefectureRepository;
import com.codestep.portfolio1app.repositories.PrefectureRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ActivityPrefectureService {
	
	@Autowired
	ActivityPrefectureRepository activityPrefectureRepository;
	
	@Autowired
	PrefectureRepository prefectureRepository;
	
	public List<ActivityPrefecture> getByUser(User user) {
		return activityPrefectureRepository.getByUser(user);
	}
	public void create(User user, String strPrefectures) {
		int[] prefectures = Stream.of(strPrefectures.split(",")).mapToInt(Integer::parseInt).toArray();
		
		for(long prefecture: prefectures) {
			ActivityPrefecture  activityPrefecture = new ActivityPrefecture();
			activityPrefecture.setUser(user);
			activityPrefecture.setPrefecture(prefectureRepository.getReferenceById(prefecture));
			activityPrefectureRepository.saveAndFlush(activityPrefecture);
		}
		
	}
}
