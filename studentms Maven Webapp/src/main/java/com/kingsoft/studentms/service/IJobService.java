package com.kingsoft.studentms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kingsoft.studentms.model.Job;

public interface IJobService {

	
	public int getJobCount(String username);
	
	public List<Job> selectJobByUsername(Map<String, Object> map);
	
	public String addJob(Map<String, Object> map);
	
	public int deleteByPrimaryKey(String jid);
	
	public int getAllJobCount(Map<String, Object> map);
	
	
	
	
	
	/**
	 * @
	 * @param job
	 * @return
	 */
	public String publishJob(Job job);
	/**
	 * @
	 * @param title
	 * @return
	 */
	public Job queryJob(String title);
	/**
	 * @
	 * @param title
	 * @return list
	 */
	public List<?> queryJobList(String title);
}
