package com.kingsoft.studentms.dao;

import java.util.Map;

public interface IStudentDao extends IBasicDao{
	public int dAddStudent(Map<String, Object> map); 
}
