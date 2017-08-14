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
	 * @根据标题查询
	 * @param title
	 * @return
	 */
	public Job queryJob(@Param("title") String title);

	/**
	 * @标题模糊查询
	 * @return
	 */
	public List<?> queryJobList(@Param("title") String title);

	/**
	 * @得到文件路径
	 * @param jid
	 * @return
	 */
	public String getFilePath(@Param("jid") int jid);
}
