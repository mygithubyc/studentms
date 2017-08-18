package com.kingsoft.studentms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/course")
@SessionAttributes("user")
public class CourseController {
	private ModelAndView modelView; // 返回一个model视图
	//显示添加课程界面
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(){
		return "admin/course/add";
	}
	//显示课程分配情况界面
	@RequestMapping(value = "/assignment", method = RequestMethod.GET)
	public String assignment(){
		return "admin/course/assignment";
	}
	//显示进行课程分配界面
	@RequestMapping(value = "/assignCourse", method = RequestMethod.GET)
	public String assignCourse(){
		return "admin/course/assign_course";
	}
	//显示教学计划安排界面
	@RequestMapping(value = "/teachingPlan", method = RequestMethod.GET)
	public String teachingPlan(){
		return "admin/course/teaching_plan";
	}
}
