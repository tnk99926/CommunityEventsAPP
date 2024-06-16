package com.codestep.portfolio1app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.codestep.portfolio1app.entities.Prefecture;
import com.codestep.portfolio1app.repositories.PrefectureRepository;
import com.codestep.portfolio1app.repositories.UserInformationRepository;
import com.codestep.portfolio1app.repositories.UserRepository;
import com.codestep.portfolio1app.services.ActivityPrefectureService;
import com.codestep.portfolio1app.services.UserInformationService;

import jakarta.transaction.Transactional;

@Controller
public class UserController {
	
	@Autowired
	private PrefectureRepository prefectureRepository;
	
	@GetMapping("/user/add")
	public ModelAndView showAddForm(ModelAndView mav) {
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
	public ModelAndView postAddForm(@RequestParam String username, @RequestParam String password, @RequestParam String email,@RequestParam long prefecture,@RequestParam("activity_prefectures") String activityPrefectures, ModelAndView mav) {
		var user = User
				.withUsername(username)
				.password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
				.roles("USER")
				.build();
		userDetailsManager.createUser(user);
		com.codestep.portfolio1app.entities.User perent = userRepository.getByUsername(username);
		
		userInformationService.create(perent, email, prefectureRepository.getReferenceById(prefecture));
		activityPrefectureService.create(perent, activityPrefectures);
		
		mav.setViewName("/user/add");
		return mav;
	}
	
}
