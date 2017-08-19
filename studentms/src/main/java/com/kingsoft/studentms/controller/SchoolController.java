package com.kingsoft.studentms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.kingsoft.studentms.model.School;
import com.kingsoft.studentms.service.ISchoolService;

@Controller
@RequestMapping("/school")
@SessionAttributes("user")
public class SchoolController {
	private ModelAndView modelView; // 返回一个model视图
	@Resource
	private ISchoolService schoolService;
	//显示添加学院界面
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(){
		return "admin/school/add";
	}
//	生成学院combobox
	@RequestMapping(value = "/dCombobox", method = RequestMethod.POST)
	public @ResponseBody List<School> dCombobox(){
		return schoolService.dCombobox();
	}
	
}
