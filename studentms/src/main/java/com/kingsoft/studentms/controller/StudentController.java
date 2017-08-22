package com.kingsoft.studentms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.kingsoft.studentms.service.IClassService;

@Controller
@RequestMapping("/student")
@SessionAttributes("user")
public class StudentController {
	private ModelAndView modelView; // 返回一个model视图
	@Resource
	private IClassService classService;
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
	
	@RequestMapping(value="dAddStudent", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> dAddStudent(String realName,String username,String gender,String birth,String idNum,String entranceTime,String schoolYear,String departId,String classId) throws ParseException{
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> addUserMap = new HashMap<String, Object>();
		Map<String, Object> addStuMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date birthDate = sdf.parse(birth);
		Date entranceDate = sdf.parse(birth);
		
		addUserMap.put("realName", realName);
		addUserMap.put("username", username);
		addUserMap.put("sex", gender);
		addUserMap.put("birth", birthDate);
		addUserMap.put("idNum", idNum);
		
		addStuMap.put("entranceTime", entranceDate);
		addStuMap.put("schoolYear", schoolYear);
		addStuMap.put("departId", departId);
		addStuMap.put("classId", classId);
		boolean b = classService.dAddStudent(addUserMap, addStuMap);
		
		map.put("success", b );
		return map;
	}
}
