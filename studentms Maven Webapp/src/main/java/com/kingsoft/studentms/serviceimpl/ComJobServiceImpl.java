package com.kingsoft.studentms.serviceimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kingsoft.studentms.dao.IComJobDao;
import com.kingsoft.studentms.model.ComJob;
import com.kingsoft.studentms.service.IComJobService;

@Service("comJobService")
public class ComJobServiceImpl implements IComJobService {
	@Resource
	private  IComJobDao comJobDao;
	
	

	public int selectComjobCount(String username) {
		// TODO Auto-generated method stub
		System.out.println("老师名字"+username);
		return comJobDao.selectComjobCount(username);
	}

	
	
	
	
	
	public String comJob(ComJob comJob) {
		// TODO Auto-generated method stub
		return null;
	}








}
