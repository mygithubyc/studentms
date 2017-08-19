package com.kingsoft.studentms.serviceimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kingsoft.studentms.dao.IDepartmentDao;
import com.kingsoft.studentms.model.Department;
import com.kingsoft.studentms.service.IDepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl implements IDepartmentService{

	@Resource
	private IDepartmentDao departmentDao;
	@Override
	public int addDepartment(Department department) {
		// TODO 自动生成的方法存根
		return departmentDao.addDepartment(department);
	}

}
