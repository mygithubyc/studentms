package com.kingsoft.studentms.serviceimpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kingsoft.studentms.dao.IComJobDao;
import com.kingsoft.studentms.model.ComJob;
import com.kingsoft.studentms.model.Submit;
import com.kingsoft.studentms.service.IComJobService;

@Service("comJobService")
public class ComJobServiceImpl extends BasicServiceImpl implements IComJobService {
	@Resource
	private  IComJobDao comJobDao;
	
	

	public int selectComjobCount(Map<String, Object> countMap ) {
		
		return comJobDao.selectComjobCount(countMap);
	}

	
	public List<Submit> selectComjob(Map<String, Object> map) {
		
		return comJobDao.selectComjob(map);
	}

	

	public String submitJob(Map<String, Object> map) {
		
		
		MultipartFile mFile = (MultipartFile) map.get("file");
		if (mFile.isEmpty()) {
			return "{\"msg\":\"请选择文件\"}";
		}		
		String filePath = uploadMutiFile(mFile, map.get("uploadPath").toString());
		if (filePath.equals("empty")) {
			return "{\"msg\":\"请选择文件\"}";
		}else if (filePath.equals("typeError")) {
			return "{\"msg\":\"只能上传zip或者rar格式的文件\"}";
		}
		ComJob comJob = (ComJob)map.get("comJob");
		comJob.setPath(filePath);
		System.out.println("JobService filePath"+filePath);
		
		
		int insertId = comJobDao.comJob(comJob);
		System.out.println(insertId);
		if (insertId > 0) {
			return "{\"success\":true}";
		}else {
			return "{\"msg\":\"服务器出错\"}";
		}
		
		
	}

	
	public boolean checkResubmit(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int count = comJobDao.selectComJobForCheck(map);
		if (count>0) {
			return false;
		}else {
			return true;
		}
	}
	
	
	
	
	
	
	
	


	







	







}
