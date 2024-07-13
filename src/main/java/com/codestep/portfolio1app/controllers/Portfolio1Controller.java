package com.codestep.portfolio1app.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Portfolio1Controller {
	
	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}
	@RequestMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView admin(ModelAndView mav) {
		mav.setViewName("admin");
		return mav;
	}
	
}
