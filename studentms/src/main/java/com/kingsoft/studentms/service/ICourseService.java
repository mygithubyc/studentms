package com.kingsoft.studentms.service;

import java.util.List;
import java.util.Map;

import com.kingsoft.studentms.model.Course;
import com.kingsoft.studentms.model.MyAssignment;
import com.kingsoft.studentms.model.MyCourse;

public interface ICourseService {
	public int addCourse(Course course);
	
	
	public List<Course> showCourseAssign(Map<String, Object> map);
	
	public int courseAssignSum(Map<String, Object> map);
	
	public List<MyAssignment> courseAssignRows(Map<String, Object> map);
	
	public int courseSum(Map<String, Object> map);
	
	public List<MyCourse> courseRows(Map<String, Object> map);
}
