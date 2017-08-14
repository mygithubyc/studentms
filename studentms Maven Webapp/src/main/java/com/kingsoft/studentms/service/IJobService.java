package com.kingsoft.studentms.service;

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
	public String queryJobList(String title);
}
