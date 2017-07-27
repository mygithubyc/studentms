package com.kingsoft.studentms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kingsoft.studentms.model.Job;

public interface IJobDao {

	/**
	 * @老师发布作业
	 * @param job
	 * @return
	 */
	public int publishJob(@Param("job") Job job);
	/**
	 * @按标题查询作业
	 * @param title
	 * @return
	 */
	public Job queryJob(@Param("title") String title);
	/**
	 * @查询所有作业
	 * @return
	 */
	public List<?> queryJobList(@Param("title") String title);
	
}
