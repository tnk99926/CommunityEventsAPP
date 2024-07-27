package com.codestep.CommunityEventsApp.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codestep.CommunityEventsApp.Exceptions.ValidationException;
import com.codestep.CommunityEventsApp.entities.ActivityPrefecture;
import com.codestep.CommunityEventsApp.entities.Prefecture;
import com.codestep.CommunityEventsApp.entities.UserInformation;
import com.codestep.CommunityEventsApp.entities.DTO.UserFormDTO;
import com.codestep.CommunityEventsApp.services.MultiTableService;
import com.codestep.CommunityEventsApp.services.PrefectureService;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private PrefectureService prefectureService;
	
	@Autowired
	MultiTableService multiTableService;
	
	@GetMapping("/user/add")
	public ModelAndView showAddForm(@ModelAttribute("formModel") UserFormDTO userFormDTO, ModelAndView mav) {
		mav.setViewName("/user/add");
		List<Prefecture> prefectures = prefectureService.findAll();
		mav.addObject("prefectures", prefectures);
		return mav;
	}
	
	@PostMapping("/user/add")
	public ModelAndView postAddForm(@Valid @ModelAttribute("formModel") UserFormDTO userFormDTO, BindingResult result, ModelAndView mav) {
		List<Prefecture> prefectures = prefectureService.findAll();
		mav.addObject("prefectures", prefectures);
		
		if(!result.hasErrors()) {
			//	ユーザ登録
			com.codestep.CommunityEventsApp.entities.User formUser = userFormDTO.getUser();
			UserInformation userInformation = userFormDTO.getUserInformation();
			
			List<Long> activityPrefectureIds = userFormDTO.getActivityPrefectureIds();
			List<ActivityPrefecture> activityPrefectures = activityPrefectureIds.stream().map(prefectureid -> new ActivityPrefecture(prefectureid)).collect(Collectors.toList());
			
			try {
				multiTableService.CreateCompleteUser(formUser, userInformation, activityPrefectures, result);
				mav.setViewName("redirect:/");
			} catch (ValidationException e) {
				mav.addObject("activityPrefectureIdsErrors", e.getBindingResult().getFieldError("prefectureId").getDefaultMessage());
				mav.setViewName("/user/add");
			}
			
		} else {
			mav.setViewName("/user/add");
		}
		return mav;
	}
	
}
