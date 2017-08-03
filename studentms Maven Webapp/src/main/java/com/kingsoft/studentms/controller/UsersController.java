package com.kingsoft.studentms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
			json = "{\"success\":true}";
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
			json = "{\"success\":true}";
		}else{
			json = "{\"success\":false}";
		}
		pw.write(json);
	}
	@RequestMapping("/doRegister")
	public void doRegister(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String username = (String)request.getParameter("username");
		String password = (String)request.getParameter("password");
		
		
//		该方法只能学生注册
		Users user = new Users(username,password,"1");
		String json = "";
		
		if (usersService.doRegister(user)) {
			json = "{\"success\":true}";
		}else {
			json = "{\"message\":\"请重试(不允许中文!)\"}";
		}
		
		
		PrintWriter pw = response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		pw.write(json);
		
		
		
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,Model model){
		HttpSession session = request.getSession();
		session.invalidate();
		model.addAttribute("error","成功登出");
		return "main/error";
	}
	
	
	@RequestMapping("/error")
	public String error(Model model){
		model.addAttribute("error","请重新登录");
		return "main/error";
	}
	
	@RequestMapping("/goMain")
	public String goMain(HttpServletRequest request,Model model){
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user")!=null){
			Users user = (Users)session.getAttribute("user");
			model.addAttribute("userType", user.getUsertType());
			return "main/main";
		}else {
			model.addAttribute("error","非法登录");
			return "main/error";
		}
		
	}
	
	/*
	 * @param 用于获取session
	 * @return bool false表示非法访问
	 */
	public boolean checkLogin(HttpServletRequest request){
		HttpSession session = request.getSession();
		if (session.getAttribute("user")!=null) {
			return true;
		}
		return false;
		
	}
}
