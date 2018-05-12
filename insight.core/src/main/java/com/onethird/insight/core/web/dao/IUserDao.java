package com.onethird.insight.core.web.dao;

import org.apache.ibatis.annotations.Param;

import com.onethird.insight.core.web.entity.UserBean;

public interface IUserDao {
	public UserBean getUserInfoById(@Param("id") Long id);
}
