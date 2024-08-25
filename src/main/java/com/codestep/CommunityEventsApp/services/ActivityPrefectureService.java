package com.codestep.CommunityEventsApp.services;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<ActivityPrefecture> findByUser(User user) {
		return activityPrefectureRepository.findByUser(user);
	}
	
	public List<Long> getActivityPrefectureIdsByUser(User user){
		List<ActivityPrefecture> activityPrefectures = activityPrefectureRepository.findByUser(user);
		List<Long> activityPrefectureIds = activityPrefectures.stream().map(activityPrefecture -> activityPrefecture.getPrefecture().getId()).collect(Collectors.toList());
		return activityPrefectureIds;
	}
	
	public String getActivityPrefectureNamesByUser(User user){
		List<ActivityPrefecture> activityPrefectures = activityPrefectureRepository.findByUser(user);
		List<String> strActivityPrefectures = activityPrefectures.stream().map(activityPrefecture -> activityPrefecture.getPrefecture().getName()).collect(Collectors.toList());
		return String.join(" ", strActivityPrefectures);
	}
	
	public void create(User user, List<Long> activityPrefectureIds,BindingResult result) {
		List<ActivityPrefecture> activityPrefectures = activityPrefectureIds.stream().map(prefectureid -> new ActivityPrefecture(prefectureid)).collect(Collectors.toList());
		
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
	
	public void update(User user, List<Long> updatedActivityPrefectureIds,BindingResult result){
		//削除する要素を抽出		
		List<Long> deletedActivityPrefectureIds = getActivityPrefectureIdsByUser(user);
		deletedActivityPrefectureIds.removeAll(updatedActivityPrefectureIds);//削除対象の要素から、更新対象にも含まれる要素を取り除く
		
		//新規保存する差分を抽出
		updatedActivityPrefectureIds.removeAll(getActivityPrefectureIdsByUser(user));//更新対象の要素から、新旧で重複する要素を取り除く
		
		//削除
		for(Long deletedActivityPrefectureId: deletedActivityPrefectureIds) {
			activityPrefectureRepository.delete(activityPrefectureRepository.findByUserAndPrefectureId(user, deletedActivityPrefectureId));
		}
		
		//新規保存
		List<ActivityPrefecture> updatedActivityPrefectures = updatedActivityPrefectureIds.stream().map(prefectureid -> new ActivityPrefecture(prefectureid)).collect(Collectors.toList());
		
		for (ActivityPrefecture updatedActivityPrefecture : updatedActivityPrefectures) {
			validator.validate(updatedActivityPrefecture, result);
		}
		if (result.hasErrors()) {
            throw new ValidationException(result);
        }
	
		for(ActivityPrefecture updatedActivityPrefecture: updatedActivityPrefectures) {
			updatedActivityPrefecture.setUser(user);
			updatedActivityPrefecture.setPrefecture(prefectureService.getById(updatedActivityPrefecture.getPrefectureId()));
			activityPrefectureRepository.saveAndFlush(updatedActivityPrefecture);
		}
		
		
		
		
		
		
	}
}
