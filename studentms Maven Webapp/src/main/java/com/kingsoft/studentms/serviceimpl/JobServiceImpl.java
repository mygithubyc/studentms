package com.kingsoft.studentms.serviceimpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sun.security.util.Debug;

import com.kingsoft.studentms.dao.IJobDao;
import com.kingsoft.studentms.model.Job;
import com.kingsoft.studentms.service.IJobService;
@Service("jobService")
public class JobServiceImpl extends BasicServiceImpl implements IJobService {
	@Resource
	private IJobDao jobDao;
	
	
	
	public int getJobCount(String username) {
	
		return jobDao.selectJobCount(username);
	}


	public List<Job> selectJobByUsername(Map<String, Object> map) {
		
		return  jobDao.selectJobByUsername(map);
	}

	
	public String addJob(Map<String, Object> map) {
		
		MultipartFile mFile = (MultipartFile) map.get("file");
		Job job = (Job)map.get("job");
		if (mFile.isEmpty()) {
			return "{\"msg\":\"请选择文件\"}";
		}
		
		String filePath = uploadMutiFile(mFile, map.get("path").toString());
		if (filePath.equals("empty")) {
			return "{\"msg\":\"请选择文件\"}";
		}else if (filePath.equals("typeError")) {
			return "{\"msg\":\"只能上传zip或者rar格式的文件\"}";
		}
		System.out.println("JobService filePath"+filePath);
		job.setPath(filePath);
		job.setSendTime(new Date());
		int insertId = jobDao.publishJob(job);
	
		if (insertId > 0) {
			return "{\"success\":true}";
		}else {
			return "{\"msg\":\"服务器出错\"}";
		}
		
	}
	
	public int deleteByPrimaryKey(String jid) {
		// TODO Auto-generated method stub
		return jobDao.deleteByPrimaryKey(jid);
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
