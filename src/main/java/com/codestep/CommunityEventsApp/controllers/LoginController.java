package com.codestep.CommunityEventsApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public ModelAndView login(ModelAndView mav, @RequestParam(value="error", required=false)String error) {
		mav.setViewName("login");
		if (error != null) {
			mav.addObject("msg", "ログインできませんでした。");
		} else {
			mav.addObject("msg","ユーザー名とパスワードを入力");
		}
		
		return mav;
	}
}
