package com.kingsoft.studentms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.kingsoft.studentms.model.Course;
import com.kingsoft.studentms.model.MyAssignment;
import com.kingsoft.studentms.model.MyCourse;
import com.kingsoft.studentms.service.ICourseService;

@Controller
@RequestMapping("/course")
@SessionAttributes("user")
public class CourseController {
	private ModelAndView modelView; // 返回一个model视图
	@Resource
	private ICourseService courseService;
	//显示添加课程界面
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(){
		return "admin/course/add";
	}
	//显示课程分配情况界面
	@RequestMapping(value = "/assignment", method = RequestMethod.GET)
	public String assignment(){
		return "admin/course/assignment";
	}
	//显示进行课程分配界面
	@RequestMapping(value = "/assignCourse", method = RequestMethod.GET)
	public String assignCourse(){
		return "admin/course/assign_course";
	}
	//显示教学计划安排界面
	@RequestMapping(value = "/teachingPlan", method = RequestMethod.GET)
	public String teachingPlan(){
		return "admin/course/teaching_plan";
	}
	//管理员进行课程录入操作
	@RequestMapping(value="/addCourse", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> addCourse(String course_name, String college_id){
		Map<String, Object> map = new HashMap<String, Object>();
		Course course = new Course();
		course.setCourseName(course_name);
		course.setSchoolId(Integer.parseInt(college_id));
		course.setCourseStatus("1");
		int insert = courseService.addCourse(course);
		if(insert > 0){
			map.put("success", true);
		}else{
			map.put("msg", "添加失败");
		}
		return map;
	}
	//管理员课程配置表格 用到自定义model MyAssignment
	@RequestMapping(value = "showCourseAssign",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> showCourseAssign(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String rows = request.getParameter("rows") != null ? (String) request.getParameter("rows") : "10";
		String page = request.getParameter("page") != null ? (String) request.getParameter("page") : "1";
		String depart_name = request.getParameter("depart_name") != null ? (String) request.getParameter("depart_name") : "";
		
		int offset = Integer.parseInt(rows)*(Integer.parseInt(page)-1);
		int limit =  offset+Integer.parseInt(rows);
		
		Map<String, Object> countMap = new HashMap<String, Object>();
		countMap.put("departName", depart_name);
		int total = courseService.courseAssignSum(countMap);
		
		Map<String, Object> selectMap = new HashMap<String, Object>();
		selectMap.put("departName", depart_name);
		selectMap.put("offset", offset);
		selectMap.put("limit", limit);
		List<MyAssignment> myAssignments = courseService.courseAssignRows(selectMap);
		
		map.put("total", total);
		map.put("rows", myAssignments);
		return map;
	}
	//显示所有课程 管理员给系分配课程左表格用 用到对象类型为自定义的
	@RequestMapping(value="/showAssignCourse", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> showAssignCourse(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String rows = request.getParameter("rows") != null ? (String) request.getParameter("rows") : "10";
		String page = request.getParameter("page") != null ? (String) request.getParameter("page") : "1";
		String courseName = request.getParameter("course_name") != null ? (String) request.getParameter("course_name") : "";
		
		int offset = Integer.parseInt(rows)*(Integer.parseInt(page)-1);
		int limit =  offset+Integer.parseInt(rows);
		
		Map<String, Object> countMap = new HashMap<String, Object>();
		countMap.put("courseName", courseName);
		int total = courseService.courseSum(countMap);
		
		Map<String, Object> selectMap = new HashMap<String, Object>();
		selectMap.put("courseName", courseName);
		selectMap.put("offset", offset);
		selectMap.put("limit", limit);
		List<MyCourse> myCourses = courseService.courseRows(selectMap);
		
		
		map.put("total", total);
		map.put("rows", myCourses);
		
		return map;
	}
	
	@RequestMapping(value="/dCourseByDepart", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> dCourseByDepart(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String rows = request.getParameter("rows") != null ? (String) request.getParameter("rows") : "10";
		String page = request.getParameter("page") != null ? (String) request.getParameter("page") : "1";
		String departId = request.getParameter("departId") != null ? (String) request.getParameter("departId") : "0";
		if (departId == "0") {
			return null;
		}
		int offset = Integer.parseInt(rows)*(Integer.parseInt(page)-1);
		int limit =  offset+Integer.parseInt(rows);
		
	
		
		Map<String, Object> selectMap = new HashMap<String, Object>();
		selectMap.put("departId", departId);
		selectMap.put("offset", offset);
		selectMap.put("limit", limit);
		map = courseService.dCourseByDepart(selectMap);

		return map;
	}
}
