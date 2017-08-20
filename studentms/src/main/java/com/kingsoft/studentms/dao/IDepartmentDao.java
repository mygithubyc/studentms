package com.kingsoft.studentms.dao;

import java.util.List;

import com.kingsoft.studentms.model.Department;

public interface IDepartmentDao extends IBasicDao{
	public int addDepartment(Department department);
	
	public List<Department> selectBySchool(String schoolId);
	
	public int selectBySchoolRows(String schoolId);

}
