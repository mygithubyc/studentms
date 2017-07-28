package com.kingsoft.studentms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingsoft.studentms.service.IJobService;

@Controller
@RequestMapping("/job")
public class JobController {

	@Resource
	private IJobService jobService;
	
	@RequestMapping("/student")
	public String student(){
		return "homework/student";
	}
	@RequestMapping("/teacher")
	public String teacher(){
		return "homework/teacher";
	}
}
