package com.kingsoft.studentms.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kingsoft.studentms.dao.ISchoolDao;
import com.kingsoft.studentms.model.School;
import com.kingsoft.studentms.service.ISchoolService;
@Service("schoolService")
public class SchoolServiceImpl implements ISchoolService{

	@Resource
	private ISchoolDao schoolDao;
	
	@Override
	public List<School> dCombobox() {
		// TODO 自动生成的方法存根
		return schoolDao.dSchoolList();
	}

	@Override
	public int getSchoolRows() {
		// TODO 自动生成的方法存根
		return schoolDao.getSchoolRows();
	}

	@Override
	public boolean dAddSchool(School school) {
	
		if (schoolDao.dSelectSchool(school.getSchoolName()).size() > 0) {
			return false;
		}
		int insert = schoolDao.dAddSchool(school);
		if (insert>0) {
			return true;
		}
		return false;
	}
	
}
