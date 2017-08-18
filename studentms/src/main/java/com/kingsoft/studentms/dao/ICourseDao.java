package com.kingsoft.studentms.dao;

import java.util.List;
import java.util.Map;

import com.kingsoft.studentms.model.Course;
import com.kingsoft.studentms.model.MyAssignment;

public interface ICourseDao extends IBasicDao {

	public int addCourse(Course course);
	
	public int courseAssignSum(Map<String, Object> map);
	
	public List<MyAssignment> selectCourseAssignment(Map<String, Object> map);
	
	/**
	 * @学生查询成绩
	 * @param studentId
	 * @return
	 */
	public List<?> getCourseList(int studentId);
}
