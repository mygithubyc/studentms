package com.kingsoft.studentms.dao;

import java.util.Map;

public interface ITeacherDao extends IBasicDao{
	public int dAddTeacher(Map<String, Object> map);
	
	public int dSetHeadTeacher(int teacherId);
}
