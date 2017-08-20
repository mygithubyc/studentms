package com.kingsoft.studentms.dao;

import java.util.Map;

public interface ICourseAssignDao {
	public int addCourseAssign(Map<String, Object> addMap);
	
	public int deleteCourseByDepary(String id);
}
