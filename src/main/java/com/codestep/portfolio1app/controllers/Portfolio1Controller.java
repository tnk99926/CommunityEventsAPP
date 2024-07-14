package com.codestep.portfolio1app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Portfolio1Controller {
	
	@Autowired
	UserDetailsManager userDetailsManager;
	
	
	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		mav.addObject("loginUserName",username);
		mav.setViewName("index");
		return mav;
	}
	@RequestMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView admin(ModelAndView mav) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		mav.addObject("loginUserName",username);
		mav.setViewName("admin");
		return mav;
	}
	
}
