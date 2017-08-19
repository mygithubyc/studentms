package com.kingsoft.studentms.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ICourseAssignService {
	public boolean addCourseAssigns(Map<String, Object> addMap);
	
	public int cancleAssignByDepart(@Param("id") String id);
}
