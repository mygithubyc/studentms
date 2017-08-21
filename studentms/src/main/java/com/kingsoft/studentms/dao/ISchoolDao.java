package com.kingsoft.studentms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kingsoft.studentms.model.School;
@Repository
public interface ISchoolDao {
	public List<School> dSchoolList();
	
	public int dAddSchool(School school);
	
	public List<School> dSelectSchool(String schoolName);
	/**
	 * @添加学院
	 * @param school
	 * @return
	 */
	public int addSchool(School school);
	public int getSchoolRows();

}
