package com.deposit.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.deposit.entity.User;
import com.deposit.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView adminLogin(User user, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String request_vcode = request.getParameter("verifyCode");
		String session_vcode = (String)request.getSession().getAttribute("validateCode");
		if(!request_vcode.equalsIgnoreCase(session_vcode)) {	
			request.getSession().setAttribute("loginError", "验证码错误！");
			mav.setViewName("redirect:/admin.do");
		} else {
			User currentUser = userService.login(user);
			if(currentUser != null){
				request.getSession().setAttribute("adminuser", currentUser);
				mav.setViewName("redirect:/standard/preAdd.do");
			} else {
				request.getSession().setAttribute("loginError", "用户名或者密码错误！");
				mav.setViewName("redirect:/admin.do");
			}			
		}
		return mav;
	}	
}
