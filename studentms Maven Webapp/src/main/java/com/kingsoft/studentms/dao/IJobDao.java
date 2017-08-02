package com.kingsoft.studentms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kingsoft.studentms.model.Job;
@Repository
public interface IJobDao {
	
	public int selectJobCount(String username);
	
	public List<Job> selectJobByUsername(Map<String, Object> map);
	
	public int deleteByPrimaryKey(String jid);
	
	
	public int selectAllJobCount();
	
	
	
	
	
	
	
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
