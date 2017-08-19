package com.kingsoft.studentms.dao;

import com.kingsoft.studentms.model.Department;

public interface IDepartmentDao extends IBasicDao{
	public int addDepartment(Department department);
}
