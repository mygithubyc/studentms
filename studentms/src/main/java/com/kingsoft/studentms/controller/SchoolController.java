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
	//显示改良院系界面系界面
	@RequestMapping(value = "/department", method = RequestMethod.GET)
	public String department(){
		return "admin/school/department";
	}
//	生成所有学院json
	@RequestMapping(value = "/dCombobox", method = RequestMethod.POST)
	public @ResponseBody List<School> dCombobox(){
		return schoolService.dCombobox();
	}
//	
	@RequestMapping(value = "/schoolDg", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> schoolDg(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<School> schools = schoolService.dCombobox();
		int total = schoolService.getSchoolRows();
		map.put("rows", schools);
		map.put("total", total);
		return map;
	}
//	添加学院
	@RequestMapping(value = "/dAddSchool", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> dAddSchool(String schoolName){
		Map<String, Object> map = new HashMap<String, Object>();
		School school = new School();
		school.setSchoolName(schoolName);
		if (schoolService.dAddSchool(school)) {
			map.put("success", true);
		}else {
			map.put("msg", "添加失败");
		}
		
		
		return map;
	}
	
}
