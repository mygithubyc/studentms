package com.kingsoft.studentms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kingsoft.studentms.model.Job;

public interface IJobDao {

	/**
	 * @��ʦ������ҵ
	 * @param job
	 * @return
	 */
	public int publishJob(@Param("job") Job job);
	/**
	 * @�������ѯ��ҵ
	 * @param title
	 * @return
	 */
	public Job queryJob(@Param("title") String title);
	/**
	 * @��ѯ������ҵ
	 * @return
	 */
	public List<?> queryJobList(@Param("title") String title);
	
}
