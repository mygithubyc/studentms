package com.kingsoft.studentms.service;

import java.util.List;
import java.util.Map;

import com.kingsoft.studentms.model.School;

public interface ISchoolService {
	public List<School> dCombobox();
	
	public int getSchoolRows();
	
	public boolean dAddSchool(School school);
}
