package com.kingsoft.studentms.serviceimpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kingsoft.studentms.dao.IComJobDao;
import com.kingsoft.studentms.model.ComJob;
import com.kingsoft.studentms.model.Submit;
import com.kingsoft.studentms.service.IComJobService;

@Service("comJobService")
public class ComJobServiceImpl implements IComJobService {
	@Resource
	private  IComJobDao comJobDao;
	
	

	public int selectComjobCount(Map<String, Object> countMap ) {
		
		return comJobDao.selectComjobCount(countMap);
	}

	
	public List<Submit> selectComjob(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return comJobDao.selectComjob(map);
	}

	
	
	
	public String comJob(ComJob comJob) {
		// TODO Auto-generated method stub
		return null;
	}






	







}
