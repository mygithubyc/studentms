package com.kingsoft.studentms.service;

import java.util.List;
import java.util.Map;

import com.kingsoft.studentms.model.Department;

public interface IDepartmentService {
	public int addDepartment(Department department);
	
	public Map<String, Object> selectBySchoolDg(String schoolId); 
}
