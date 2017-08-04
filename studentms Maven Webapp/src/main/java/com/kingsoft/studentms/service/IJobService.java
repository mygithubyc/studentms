package com.kingsoft.studentms.service;

import java.util.List;
import java.util.Map;

import com.kingsoft.studentms.model.Job;

public interface IJobService {

	
	public int getJobCount(String username);
	
	public List<Job> selectJobByUsername(Map<String, Object> map);
	
	public String addJob(Map<String, Object> map);
	
	public int deleteByPrimaryKey(String jid);
	
	public int getAllJobCount(Map<String, Object> map);
	
	public List<Job> stuGetAllJob(Map<String, Object> map);
	
	public String updateJob(Map<String, Object> map);
	
	
}
