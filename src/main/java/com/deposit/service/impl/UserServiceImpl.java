package com.deposit.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.deposit.dao.UserDao;
import com.deposit.entity.User;
import com.deposit.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;
	
	public User login(User user) {
		return userDao.login(user);
	}
	
}
