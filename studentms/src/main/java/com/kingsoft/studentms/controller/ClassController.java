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
	
	@RequestMapping(value="/dNotTeacherCombobox",method = RequestMethod.POST)
	public @ResponseBody List<MyDTeacher>  dNotTeacherCombobox(){
		return classService.dNotTeacherCombobox();
		
	}
	@RequestMapping(value="/dTeacherCombobox",method = RequestMethod.POST)
	public @ResponseBody List<MyDTeacher>  dTeacherCombobox(){
		return classService.dTeacherCombobox();
		
	}
	@RequestMapping(value="/dAddClass",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object>  dAddClass(String className,String buildYear,String teacherId,String departId){
		Map<String, Object> map = new HashMap<String,Object>();
		Class class1 = new Class();
		class1.setBuildYear(Integer.parseInt(buildYear));
		class1.setTeacherId(Integer.parseInt(teacherId));
		class1.setDepartId(Integer.parseInt(departId));
		class1.setClassName(className);
		class1.setClassStatus("1");
		boolean b =  classService.dAddClass(class1);
		if (b) {
			map.put("success", true);
		}else{
			map.put("success", false);
		}
		return map;
		
	}
}
