package com.kingsoft.studentms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student")
@SessionAttributes("user")
public class StudentController {
	private ModelAndView modelView; // 返回一个model视图
	//显示添加学生界面
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(){
		return "admin/student/add";
	}
	//显示提交作业界面
	@RequestMapping(value = "/job")
	public String job(){
		return "home/student/job";
	}
	//显示查看成绩界面
	@RequestMapping(value = "/mark")
	public String mark(){
		return "home/student/mark";
	}
}
