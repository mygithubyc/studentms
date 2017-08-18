package com.kingsoft.studentms.dao;

import com.kingsoft.studentms.model.School;

public interface ISchoolDao {

	/**
	 * @添加学院
	 * @param school
	 * @return
	 */
	public int addSchool(School school);
}
