package com.kingsoft.studentms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kingsoft.studentms.model.Job;
@Repository
public interface IJobDao {
	
	public int selectJobCount();
	
	public List<Job> selectJobByUsername(@Param("username") String username) ;
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
	public List<Job> queryJobList(@Param("title") String title);
	
}
