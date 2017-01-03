package com.deposit.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AdminLoginInterceptor implements HandlerInterceptor{
	 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
    		Object handler) throws Exception {
    	if (request.getSession().getAttribute("adminuser") == null) {
    		request.getSession().setAttribute("loginError", "请重新登录！");
    		response.sendRedirect("/deposit/admin.do");
    		return false;
    	} else {
    		return true;       		
    	}
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, 
    		Object handler, ModelAndView modelAndView) throws Exception {
    	
    }
    
    @Override  
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
    		Object handler, Exception ex) throws Exception {  
                  
    } 
}
