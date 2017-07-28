package com.kingsoft.studentms.service;

import java.util.List;

import com.kingsoft.studentms.model.Job;

public interface IJobService {

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
