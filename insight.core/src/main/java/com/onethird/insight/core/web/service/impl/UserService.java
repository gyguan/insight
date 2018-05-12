package com.onethird.insight.core.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onethird.insight.core.web.dao.IUserDao;
import com.onethird.insight.core.web.entity.UserBean;
import com.onethird.insight.core.web.service.IUserService;

@Service("userService")
public class UserService implements IUserService{
	
	@Autowired
	private IUserDao userDao;
	
	@Override
	public UserBean getUserInfo(Long id) {
		UserBean userBean = userDao.getUserInfoById(id);
		return userBean;
	}

}
