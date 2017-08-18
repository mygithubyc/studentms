package com.kingsoft.studentms.dao;

import java.util.List;

public interface ICourseDao extends IBasicDao {

	/**
	 * @学生查询成绩
	 * @param studentId
	 * @return
	 */
	public List<?> getCourseList(int studentId);
}
