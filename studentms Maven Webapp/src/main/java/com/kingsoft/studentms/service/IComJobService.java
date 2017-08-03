package com.kingsoft.studentms.service;

import java.util.List;
import java.util.Map;

import com.kingsoft.studentms.model.Submit;

public interface IComJobService {

	
	public int selectComjobCount(Map<String, Object> countMap );
	
	public List<Submit> selectComjob(Map<String, Object> map);

	public String submitJob(Map<String, Object> map);
	
	public boolean checkResubmit(Map<String, Object> map);
	
	
	
}
