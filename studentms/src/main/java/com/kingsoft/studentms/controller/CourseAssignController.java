package com.kingsoft.studentms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.kingsoft.studentms.service.ICourseAssignService;

@Controller
@RequestMapping("/courseAssign")
@SessionAttributes("user")
public class CourseAssignController {
	private ModelAndView modelView; // 返回一个model视图
	
	@Resource
	private ICourseAssignService courseAssignService;
	
	@RequestMapping(value="/addCourseAssign", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addCourseAssign(String ids,String semester, String departId){
		Map<String, Object> map = new HashMap<String, Object>();
		
		Map<String, Object> addMap = new HashMap<String, Object>();
		addMap.put("ids", ids);
		addMap.put("semester", semester);
		addMap.put("departId", departId);
		boolean rst = courseAssignService.addCourseAssigns(addMap);
		
		map.put("success", rst);
		return map;
	}
	@RequestMapping(value="cancleAssignByDepart", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> cancleAssignByDepart(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		
		int del = courseAssignService.cancleAssignByDepart(id);
		if (del>0) {
			map.put("success", true);
		}else{
			map.put("msg", "取消失败");
		}
		
		return map;
	}
}
