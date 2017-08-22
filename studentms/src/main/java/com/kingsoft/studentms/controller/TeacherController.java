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
@RequestMapping("/teacher")
@SessionAttributes("user")
public class TeacherController {
	private ModelAndView modelView; // 返回一个model视图
	@Resource
	private IClassService classService;
	//显示添加老师界面
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(){
		return "admin/teacher/add";
	}
	//显示录入成绩界面
	@RequestMapping(value = "/main")
	public String main(){
		return "home/main";
	}
	//显示录入成绩界面
	@RequestMapping(value = "/mark")
	public String mark(){
		return "home/teacher/mark";
	}
	//显示布置作业界面
	@RequestMapping(value = "/job")
	public String job(){
		return "home/teacher/job";
	}
	//显示下载教学计划界面
	@RequestMapping(value = "/plan")
	public String plan(){
		return "home/teacher/plan";
	}
	@RequestMapping(value="dAddTeacher", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> dAddStudent(String realName,String username,String gender,String birth,String idNum,String entry_time,String departId) throws ParseException{
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> addUserMap = new HashMap<String, Object>();
		Map<String, Object> addTeachMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date birthDate = sdf.parse(birth);
		Date entryDate = sdf.parse(entry_time);
		
		addUserMap.put("realName", realName);
		addUserMap.put("username", username);
		addUserMap.put("sex", gender);
		addUserMap.put("birth", birthDate);
		addUserMap.put("idNum", idNum);
		
		addTeachMap.put("entryDate", entryDate);
		addTeachMap.put("departId", departId);
		boolean b = classService.dAddTeacher(addUserMap, addTeachMap);
		
		
		map.put("success", b);
		return map;
	}
}
