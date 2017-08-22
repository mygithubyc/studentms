package com.kingsoft.studentms.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.kingsoft.studentms.model.Job;
import com.kingsoft.studentms.model.UserInfo;
import com.kingsoft.studentms.service.ICommitInfoService;
import com.kingsoft.studentms.service.IJobService;

@Controller
@RequestMapping("/job")
public class JobController {

	@Resource
	private IJobService jobService;
	@Resource
	private ICommitInfoService commitInfoService;

	private Job job;

	@RequestMapping("/publishJob")
	public String publishJob() {
		return "common/teacher_job";
	}

	/**
	 * @发布作业
	 * @param title
	 * @param content
	 * @param deadTime
	 * @param path
	 * @return
	 */
	@RequestMapping(value = "/publishJob", method = RequestMethod.POST)
	@ResponseBody
	public String publishJob(String title, String content, String deadTime, @RequestParam MultipartFile file,
			HttpServletRequest request, HttpSession session) throws IOException {

		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(deadTime);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		job = new Job();
		job.setContent(content);
		job.setDeadTime(date);
		job.setPublishTime(new Date());
		job.setTitle(title);
		job.setFile(file);
		// 调用service
		String mesg = jobService.publishJob(job, session);
		System.out.println("请求到upload:   " + deadTime);
		System.out.println("file    :" + file);
		Map<String, Object> map = new HashMap<>();
		if (mesg == null)
			map.put("success", "success");
		else
			map.put("success", "failed");
		return JSON.toJSONString(map);
	}

	@RequestMapping(value = "/getJobList", method = RequestMethod.POST)
	@ResponseBody
	public String getJobList(ModelMap modelMap) {
		// 获得session
		UserInfo userInfo = (UserInfo) modelMap.get("user");
		System.out.println("session: " + userInfo.getUsername());
		return jobService.getJobList(userInfo.getUsername(), new Date());
	}

	@RequestMapping(value = "/getCommitByPid", method = RequestMethod.GET)
	@ResponseBody
	public String getCommitByPid(String publishId) {
		return commitInfoService.getCommitByPid(publishId);
	}
}
