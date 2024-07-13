package com.codestep.portfolio1app.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codestep.portfolio1app.Exceptions.ValidationException;
import com.codestep.portfolio1app.entities.ActivityPrefecture;
import com.codestep.portfolio1app.entities.Prefecture;
import com.codestep.portfolio1app.entities.DTO.UserFormDTO;
import com.codestep.portfolio1app.repositories.PrefectureRepository;
import com.codestep.portfolio1app.repositories.UserInformationRepository;
import com.codestep.portfolio1app.repositories.UserRepository;
import com.codestep.portfolio1app.services.ActivityPrefectureService;
import com.codestep.portfolio1app.services.UserInformationService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private PrefectureRepository prefectureRepository;
	
	@GetMapping("/user/add")
	public ModelAndView showAddForm(@ModelAttribute("formModel") UserFormDTO userFormDTO, ModelAndView mav) {
		mav.setViewName("/user/add");
		List<Prefecture> prefectures = prefectureRepository.findAll();
		mav.addObject("prefectures", prefectures);
		return mav;
	}
	
	@Autowired
	UserDetailsManager userDetailsManager;
	
	@Autowired
	UserInformationRepository userInformationRepository;
	
	@Autowired
	UserInformationService userInformationService;
	
	@Autowired
	ActivityPrefectureService activityPrefectureService;
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	@PostMapping("/user/add")
	public ModelAndView postAddForm(@Valid @ModelAttribute("formModel") UserFormDTO userFormDTO, BindingResult result, ModelAndView mav) {
		if(!result.hasErrors()) {
			//	ユーザ登録
			com.codestep.portfolio1app.entities.User formUser = userFormDTO.getUser();
			
			if (userDetailsManager.userExists(formUser.getUsername())) {//ユーザ名がすでに存在しないことを確認
				 List<Prefecture> prefectures = prefectureRepository.findAll();
					mav.addObject("prefectures", prefectures);
					mav.addObject("userExistsError", "このユーザー名はすでに使用されています");
					mav.setViewName("/user/add");
					return mav;
	        }
			var user = User
					.withUsername(formUser.getUsername())
					.password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(formUser.getPassword()))
					.roles("USER")
					.build();	
			userDetailsManager.createUser(user);
			
			//ユーザ情報登録
			com.codestep.portfolio1app.entities.User perent = userRepository.getByUsername(user.getUsername());
			userInformationService.create(perent, userFormDTO.getUserInformation());
			
			List<Long> activityPrefectureIds = userFormDTO.getActivityPrefectureIds();
			List<ActivityPrefecture> activityPrefectures = activityPrefectureIds.stream().map(prefectureid -> new ActivityPrefecture(prefectureid)).collect(Collectors.toList());

			try {
				if(activityPrefectures != null) {
					activityPrefectureService.create(perent, activityPrefectures,result);
				}
				mav.setViewName("redirect:/");
				return mav;
			} catch (ValidationException e) {
				List<Prefecture> prefectures = prefectureRepository.findAll();
				mav.addObject("prefectures", prefectures);
				mav.addObject("activityPrefectureIdsErrors", e.getBindingResult().getFieldError("prefectureId").getDefaultMessage());
				mav.setViewName("/user/add");
				return mav;
			}
			
		} else {
			List<Prefecture> prefectures = prefectureRepository.findAll();
			mav.addObject("prefectures", prefectures);
			mav.setViewName("/user/add");
			return mav;
		}
		
	}
	
}
