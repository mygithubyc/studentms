package com.kingsoft.studentms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/job")
public class JobController {

	@RequestMapping("/publishJob")
	public String publishJob() {
		return "common/teacher_job";
	}
}
