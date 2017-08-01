package com.kingsoft.studentms.service;

import java.util.List;

import com.kingsoft.studentms.model.Job;

public interface IJobService {

	
	public int getJobCount(String username);
	
	public List<Job> selectJobByUsername(String username);
	
	
	
	
	
	
	
	
	
	
	
	
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
