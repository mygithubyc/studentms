package com.kingsoft.studentms.serviceimpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sun.security.util.Debug;

import com.kingsoft.studentms.dao.IJobDao;
import com.kingsoft.studentms.model.Job;
import com.kingsoft.studentms.service.IJobService;
@Service("jobService")
public class JobServiceImpl implements IJobService {
	@Resource
	private IJobDao jobDao;
	
	
	
	public int getJobCount(String username) {
		// TODO Auto-generated method stub
		System.out.println("执行获取总行数");
		
		return jobDao.selectJobCount(username);
	}


	public List<Job> selectJobByUsername(Map<String, Object> map) {
		// TODO Auto-generated method stub
//		System.out.println(username);
		return  jobDao.selectJobByUsername(map);
	}

	

	

	
	
	
	
	public String publishJob(Job job) {
		// TODO Auto-generated method stub
		return null;
	}

	public Job queryJob(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<?> queryJobList(String title) {
		// TODO Auto-generated method stub
		return null;
	}



















	
	
	
}
