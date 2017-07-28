package com.kingsoft.studentms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingsoft.studentms.service.IComJobService;

@Controller
@RequestMapping("/comJob")
public class ComJobController {

	@Resource
	private IComJobService comJobService;
	
	@RequestMapping("/teacher")
	public String teacher(){
		return "comjob/teacher";
	}
}
