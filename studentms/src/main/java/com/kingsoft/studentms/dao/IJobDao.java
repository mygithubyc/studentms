package com.kingsoft.studentms.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IJobDao extends IBasicDao {

	/**
	 * @获得本学期
	 * @param username
	 * @param date
	 * @return
	 */
	public List<?> getJobList(@Param("username") String username, @Param("date") Date date);

	/**
	 * @获得教学计划ID
	 * @param userName
	 * @param term
	 * @return
	 */
	public List<?> getTeachId(@Param("userName") String userName, @Param("date") Date date);
	
	
}
