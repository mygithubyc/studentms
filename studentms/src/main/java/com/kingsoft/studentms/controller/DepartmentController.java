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

import com.kingsoft.studentms.model.Department;
import com.kingsoft.studentms.service.IDepartmentService;

@Controller
@RequestMapping("/department")
@SessionAttributes("user")
public class DepartmentController {
	private ModelAndView modelView; // 返回一个model视图
	@Resource
	private IDepartmentService departmentService;
	//显示添加系界面
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(){
		return "admin/department/add";
	}
	
//	管理员添加学院
	@RequestMapping(value = "/addDepartment", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addDepartment(String school_id, String depart_name){
		Map<String, Object> map =  new HashMap<String,Object>();
		Department department = new Department();
		department.setSchoolId(Integer.parseInt(school_id));
		department.setDepartName(depart_name);
		int insert = departmentService.addDepartment(department);
		if (insert > 0) {
			map.put("success", true);
		}else{
			map.put("msg", "添加失败");
		}
		
		return map;
	}
//	管理员 改良学院操作 学院所属系 detailview
	@RequestMapping(value = "/showDepartmentDg", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> showDepartmentDg(String schoolId){
		
		return departmentService.selectBySchoolDg(schoolId);
	}

}
