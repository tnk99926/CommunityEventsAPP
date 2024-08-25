package com.codestep.CommunityEventsApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codestep.CommunityEventsApp.Exceptions.ValidationException;
import com.codestep.CommunityEventsApp.entities.Prefecture;
import com.codestep.CommunityEventsApp.entities.UserInformation;
import com.codestep.CommunityEventsApp.entities.DTO.UserFormDTO;
import com.codestep.CommunityEventsApp.services.ActivityPrefectureService;
import com.codestep.CommunityEventsApp.services.MultiTableService;
import com.codestep.CommunityEventsApp.services.PrefectureService;
import com.codestep.CommunityEventsApp.services.UserInformationService;
import com.codestep.CommunityEventsApp.services.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private PrefectureService prefectureService;
	
	@Autowired
	MultiTableService multiTableService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserInformationService userInformationService;
	
	@Autowired
	ActivityPrefectureService activityPrefectureService;
	
	@Autowired
	UserDetailsManager userDetailsManager;
	
	@GetMapping("/user/add")
	public ModelAndView showAddForm(@ModelAttribute("formModel") UserFormDTO userFormDTO, ModelAndView mav) {
		mav.setViewName("/user/add");
		List<Prefecture> prefectures = prefectureService.findAll();
		mav.addObject("prefectures", prefectures);
		return mav;
	}
	
	@PostMapping("/user/add")
	public ModelAndView postAddForm(@Valid @ModelAttribute("formModel")UserFormDTO userFormDTO, BindingResult result, ModelAndView mav) {
		List<Prefecture> prefectures = prefectureService.findAll();
		mav.addObject("prefectures", prefectures);
		com.codestep.CommunityEventsApp.entities.User formUser = userFormDTO.getUser();
		
		if (userDetailsManager.userExists(formUser.getUsername())) {
			mav.addObject("userExistingError", "このユーザー名はすでに使用されています");
			mav.setViewName("/user/add");
			return mav;
        }
		
		if(!result.hasErrors()) {
			//	ユーザ登録
			UserInformation userInformation = userFormDTO.getUserInformation();
			
			List<Long> activityPrefectureIds = userFormDTO.getActivityPrefectureIds();
			
			try {
				multiTableService.createCompleteUser(formUser, userInformation, activityPrefectureIds, result);
				mav.setViewName("redirect:/");
			} catch (ValidationException e) {
				if(e.getBindingResult().getFieldError("prefectureId") != null) {
					mav.addObject("activityPrefectureIdsErrors", e.getBindingResult().getFieldError("prefectureId").getDefaultMessage());
				}
				mav.setViewName("/user/add");
			}
			
		} else {
			mav.setViewName("/user/add");
		}
		return mav;
	}
	
	@PreAuthorize("#username == authentication.principal.username")
	@GetMapping("/user/information/{username}")
	public ModelAndView showUserInformation(@PathVariable String username, ModelAndView mav) {
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		com.codestep.CommunityEventsApp.entities.User user = userService.getByUsername(username);
		UserInformation userInformation = userInformationService.getByUsername(username);
		mav.addObject("loginUserName",loginUsername);
		mav.addObject("email", userInformation.getEmail());
		mav.addObject("prefecture", userInformation.getPrefecture().getName());
		mav.addObject("activityPrefectures", activityPrefectureService.getActivityPrefectureNamesByUser(user));
		mav.setViewName("/user/information");
		return mav;
	}
	
	@PreAuthorize("#username == authentication.principal.username")
	@GetMapping("/user/edit/{username}")
	public ModelAndView showEditForm(@ModelAttribute("formModel") UserFormDTO userFormDTO,@PathVariable String username, ModelAndView mav) {
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		mav.addObject("loginUserName",loginUsername);
		com.codestep.CommunityEventsApp.entities.User user = userService.getByUsername(loginUsername);
		userFormDTO.user.setUsername(loginUsername);
		
		UserInformation userInformation = userInformationService.getByUsername(loginUsername);
		userFormDTO.setUserInformation(userInformation);
		
		List<Long> activityPrefectureIds = activityPrefectureService.getActivityPrefectureIdsByUser(user);
		userFormDTO.setActivityPrefectureIds(activityPrefectureIds);
		
		List<Prefecture> prefectures = prefectureService.findAll();
		mav.addObject("prefectures", prefectures);
		
		mav.setViewName("/user/edit");
		return mav;
	}
	
	@PreAuthorize("#username == authentication.principal.username")
	@PostMapping("/user/edit/{username}")
	public ModelAndView postEditForm(@Valid @ModelAttribute("formModel") UserFormDTO userFormDTO, BindingResult result,@PathVariable String username, ModelAndView mav) {
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Prefecture> prefectures = prefectureService.findAll();
		mav.addObject("prefectures", prefectures);
		mav.addObject("loginUserName",loginUsername);
		if(!result.hasErrors()) {
		
			com.codestep.CommunityEventsApp.entities.User user = userService.getByUsername(username);
			UserInformation userInformation = userFormDTO.getUserInformation();
			List<Long> activityPrefectureIds = userFormDTO.getActivityPrefectureIds();
			
			try {
				multiTableService.updateCompleteUser(user, userInformation, activityPrefectureIds, result);
				mav.setViewName("redirect:/user/information/{username}");
			} catch (ValidationException e) {
				if(e.getBindingResult().getFieldError("prefectureId") != null) {
					mav.addObject("activityPrefectureIdsErrors", e.getBindingResult().getFieldError("prefectureId").getDefaultMessage());
				}
				mav.setViewName("/user/edit");
			}
			
		} else {
			mav.setViewName("/user/edit");
		}
		return mav;
	}
}
