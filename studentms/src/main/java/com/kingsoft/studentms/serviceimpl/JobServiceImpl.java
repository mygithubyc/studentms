package com.kingsoft.studentms.serviceimpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.kingsoft.studentms.basic.BasicService;
import com.kingsoft.studentms.basic.ConstantUtil;
import com.kingsoft.studentms.dao.ICommitInfoDao;
import com.kingsoft.studentms.dao.IJobDao;
import com.kingsoft.studentms.model.Job;
import com.kingsoft.studentms.model.UserInfo;
import com.kingsoft.studentms.service.IJobService;

@Service("jobService")
public class JobServiceImpl extends BasicService implements IJobService {

	
	@Resource
	private IJobDao jobDao;
	@Resource
	private ICommitInfoDao commitInfoDao;

	@Override
	public String publishJob(Job job, HttpSession session) {
		// TODO Auto-generated method stub

		// 获得session取到登录用户的账号
		UserInfo user = (UserInfo) session.getAttribute("user");
		System.out.println("user " + user.getUsername());

		List<?> teachIdList = this.getTeachId(user.getUsername(), new Date());
		System.out.println("teachId: " + teachIdList);
		String filePath = null; // 文件上传的真实路径包括文件名
		try {
			filePath = uploadMutiFile(job.getFile(), ConstantUtil.TEACHER_UPLOAD_PATH);
			System.out.println("老师发布作业路径：" + filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int success = 0;
		for (Object teachId : teachIdList) {
			job.setTeachId((int) teachId); // 强制转换
			job.setPath(filePath);
			success += jobDao.add(job);
		}

		System.out.println("success: " + success + "  teachIdList.size()" + teachIdList.size());
		if (success == teachIdList.size()) // 每个班都发布成功
			return null;
		return "error";
	}

	@Override
	public String getJobList(String username, Date date) {
		// TODO Auto-generated method stub
		List<Job> jobList = (List<Job>) jobDao.getJobList(username, date); // 得到发布作业
		// List<CommitInfo> commitList = (List<CommitInfo>)
		// commitInfoDao.getCommitList();

		/*
		 * LinkedHashMap<String, List<DevInfo>> map = new LinkedHashMap<>(); for
		 * (DevInfo li : list) {
		 * //将需要归类的属性与map中的key进行比较，如果map中有该key则添加bean如果没有则新增key if
		 * (map.containsKey(li.getClassID())) {
		 * //取出map中key对应的list并将遍历出的bean放入该key对应的list中 ArrayList<DevInfo>
		 * templist = (ArrayList<DevInfo>) map.get(li.getClassID());
		 * templist.add(li); } else { //创建新的list ArrayList<DevInfo> temlist =
		 * new ArrayList<DevInfo>(); temlist.add(li); map.put(li.getClassID(),
		 * temlist); } } return map;
		 */
		// 对数据进行装配

		return JSON.toJSONString(jobList);
	}

	/**
	 * @得到教学计划IDList
	 * @param userNmae
	 * @param term
	 * @return
	 */
	@Override
	public List<?> getTeachId(String userNmae, Date date) {
		System.out.println("userNmae "+userNmae + " date"+date);
		List<?> teachIdList = jobDao.getTeachId(userNmae, date);
		System.out.println("teachIdList "+teachIdList);
		// 判断教学计划是否在当前学期安排在当前学期有课程安排
		if (teachIdList.size() == 0 || teachIdList.isEmpty())
			return null;
		return teachIdList;
	}
}
