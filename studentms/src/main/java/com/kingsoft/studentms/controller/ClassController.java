package com.kingsoft.studentms.controller;

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

import com.kingsoft.studentms.model.Class;
import com.kingsoft.studentms.model.MyDTeacher;
import com.kingsoft.studentms.service.IClassService;

@Controller
@RequestMapping("/class")
@SessionAttributes("user")
public class ClassController {
	private ModelAndView modelView; // 返回一个model视图
	
	@Resource
	private IClassService classService;
	//显示添加班级界面
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(){
		return "admin/class/add";
	}
	
	@RequestMapping(value="/dClassCombobox",method = RequestMethod.POST)
	public @ResponseBody List<Class>  dClassCombobox(String departId){
		return classService.dClassCombobox(departId);
		
	}
	
	@RequestMapping(value="/dTeacherCombobox",method = RequestMethod.POST)
	public @ResponseBody List<MyDTeacher>  dTeacherCombobox(){
		return classService.dTeacherCombobox();
		
	}
}
