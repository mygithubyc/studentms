package com.kingsoft.studentms.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.kingsoft.studentms.model.Job;

public interface IJobService {

	/**
	 * @老师发布作业
	 * @param job
	 * @return
	 */
	public String publishJob(Job job, HttpSession session);

	/**
	 * @查找本学期老师发布的作业
	 * @param username
	 * @param date
	 * @return
	 */
	public String getJobList(String username, Date date);

	/**
	 * @获得
	 * @param userNmae
	 * @param term
	 * @return
	 */
	public List<?> getTeachId(String userNmae, Date date);

}
