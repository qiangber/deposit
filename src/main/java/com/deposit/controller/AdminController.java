package com.deposit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deposit.service.StandardService;

@Controller
public class AdminController {
	
	@Autowired
	StandardService standardService;
	
	@RequestMapping("/admin")
	public String admin() {
		return "login";
	}
	
	@RequestMapping("/front")
	public ModelAndView front() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("margin");
		mav.addObject("exchanges", standardService.getExchangeList());
		return mav;
	}
}
