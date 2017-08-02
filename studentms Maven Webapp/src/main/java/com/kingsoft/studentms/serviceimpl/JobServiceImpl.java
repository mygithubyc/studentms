package com.kingsoft.studentms.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kingsoft.studentms.dao.IJobDao;
import com.kingsoft.studentms.model.Job;
import com.kingsoft.studentms.service.IJobService;
@Service("jobService")
public class JobServiceImpl implements IJobService {
	private IJobDao jobDao;
	
	
	
	public int getJobCount(String username) {
		// TODO Auto-generated method stub
		System.out.println(username);
		return jobDao.selectJobCount(username);
	}


	public List<Job> selectJobByUsername(String username) {
		// TODO Auto-generated method stub
		System.out.println(username);
		return  null;
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
