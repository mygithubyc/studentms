package com.kingsoft.studentms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.kingsoft.studentms.model.Users;
import com.kingsoft.studentms.service.IUsersService;

@Controller // 注入controller
@RequestMapping("/users") // 注入并配置controller总开关


public class UsersController {

	
	@Resource
	private IUsersService usersService; // 注入service

	@RequestMapping("/doLogin")
	public void doLogin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String username = (String)request.getParameter("username");
		String password = (String)request.getParameter("password");
		String type = (String)request.getParameter("type");
		
		Users user = usersService.getUserByUsername(username);
		String json = "";
		PrintWriter pw = response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		if (user != null && user.getPassword().equals(password)&&user.getUsertType().equals(type)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			json = "{\"success\":\"true\"}";
		}else{
			json = "{\"message\":\"请重试!\"}";
		}
//		json = JSON.toJSONString(user);
		pw.write(json);
	}
	@RequestMapping("/testUsername")
	public void testUsername(HttpServletRequest request,HttpServletResponse response) throws IOException{

		String username = (String)request.getParameter("username");
		
		Users user = usersService.getUserByUsername(username);
		String json = "";
		PrintWriter pw = response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		if (user != null){
			json = "{\"success\":\"true\"}";
		}else{
			json = "{\"success\":\"false\"}";
		}
		pw.write(json);
	}
	@RequestMapping("/doRegister")
	public void doRegister(HttpServletRequest request,HttpServletResponse response){
		String username = (String)request.getParameter("username");
		String password = (String)request.getParameter("password");
		String type = (String)request.getParameter("password_again");
		
		
	}
	
	@RequestMapping("/goMain")
	public String goMain(){
		return "main/main";
	}
}
