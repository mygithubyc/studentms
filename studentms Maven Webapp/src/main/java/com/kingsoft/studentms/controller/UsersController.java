package com.kingsoft.studentms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.kingsoft.studentms.model.Users;
import com.kingsoft.studentms.service.IUsersService;

@Controller // 注入controller
@RequestMapping("/users") // 注入并配置controller总开关


public class UsersController {

	
	@Resource
	private IUsersService usersService; // 注入service

	@RequestMapping("/doLogin")
	public void doLogin(HttpServletRequest request,HttpServletResponse response){
		
	}
	
	@RequestMapping("/goMain")
	public String goMain(){
		return "main/main";
	}
}
