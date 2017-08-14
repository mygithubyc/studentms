package com.kingsoft.studentms.serviceimpl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingsoft.studentms.basic.BasicService;
import com.kingsoft.studentms.dao.IJobDao;
import com.kingsoft.studentms.model.Job;
import com.kingsoft.studentms.service.IJobService;

@Service("jobService")
public class JobServiceImpl extends BasicService implements IJobService {

	@Resource
	private IJobDao jobDao;

	@Override
	public String publishJob(Job job) {
		// TODO Auto-generated method stub
		if (job.getFile().isEmpty())
			return "文件为空，请选择文件在提交";
		try {
			String filePath = uploadMutiFile(job.getFile(), job.getPath());
			System.out.println("filePath-----" + filePath);
			job.setPath(filePath); // 重新装载文件路径
			int status = jobDao.publishJob(job);
			if (status > 0)
				return null;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return null;
	}

	@Override
	public Job queryJob(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String queryJobList(String title) {
		// TODO Auto-generated method stub
		List<Job> jobLists = (List<Job>) jobDao.queryJobList(title);
		System.out.println("jobLists " + jobLists.toString());
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		String file;
		String jsonstr;
		for (Job job : jobLists) {
			file = job.getPath();
			file = file.substring(file.lastIndexOf("\\") + 1, file.length());
			//对文件名进行解码
			try {
				file=URLDecoder.decode(file, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			job.setPath(file); // 重新设置文件名
			jsonstr = JSON.toJSONString(job); // 得到JSON字符串
			jsonObject = JSONObject.parseObject(jsonstr); // 得到JSON对象
			jsonArray.add(jsonObject);
		}
		System.out.println("JSON.toJSONString(jsonArray)  " + JSON.toJSONString(jsonArray));
		return JSON.toJSONString(jsonArray);
	}
}