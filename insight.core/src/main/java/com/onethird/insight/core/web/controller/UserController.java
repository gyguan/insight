package com.onethird.insight.core.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onethird.insight.core.common.entity.ResultBean;
import com.onethird.insight.core.common.handler.NormalResponseHandler;
import com.onethird.insight.core.web.entity.UserBean;
import com.onethird.insight.core.web.service.IUserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user/api")
public class UserController extends NormalResponseHandler{
	@Autowired
	private IUserService userService;
	
	@ApiOperation("通过用户id获取用户信息")
	@GetMapping("/getUserInfoById")
	public ResultBean<UserBean> getUserInfoById(
			@ApiParam(name = "id", required = true)
			@RequestParam("id") Long id){
		UserBean userBean = userService.getUserInfo(id);
		return response(userBean);
	}
	
	@ApiOperation("通过用户id获取用户信息")
	@GetMapping("/hello")
	public ResultBean<String> helloWorld(){
		return response("hello world!");
	}
}
