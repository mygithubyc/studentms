package com.kingsoft.studentms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/teacher")
@SessionAttributes("user")
public class TeacherController {
	private ModelAndView modelView; // 返回一个model视图
	//显示添加老师界面
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(){
		return "admin/teacher/add";
	}
	//显示录入成绩界面
	@RequestMapping(value = "/main")
	public String main(){
		return "home/main";
	}
	//显示录入成绩界面
	@RequestMapping(value = "/mark")
	public String mark(){
		return "home/teacher/mark";
	}
	//显示布置作业界面
	@RequestMapping(value = "/job")
	public String job(){
		return "home/teacher/job";
	}
	//显示下载教学计划界面
	@RequestMapping(value = "/plan")
	public String plan(){
		return "home/teacher/plan";
	}
}
