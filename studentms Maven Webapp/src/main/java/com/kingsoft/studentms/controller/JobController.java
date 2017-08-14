package com.kingsoft.studentms.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.kingsoft.studentms.basic.DownloadService;
import com.kingsoft.studentms.model.Job;
import com.kingsoft.studentms.model.Users;
import com.kingsoft.studentms.service.IJobService;

@Controller
@RequestMapping("/job")
public class JobController {

	@Resource
	private IJobService jobService;

	private Job job;
	// private JobServiceImpl jobServiceImpl;

	public JobController() {
		System.out.println("JobController 实例化");
		// TODO Auto-generated constructor stub
	}

	/**
	 * @发布作业
	 * @param title
	 * @param content
	 * @param deadTime
	 * @param path
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(String title, String content, String deadTime, @RequestParam MultipartFile file,
			HttpServletRequest request, HttpSession session) throws IOException {

		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(deadTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 获得session取到登录用户的账号
		Users user = (Users) session.getAttribute("user");
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/teacherupload");
		job = new Job();
		job.setContent(content);
		job.setDeadTime(date);
		job.setSendTime(new Date());
		job.setTitle(title);
		job.setUsername(user.getUsername());
		job.setPath(realPath);
		job.setFile(file);
		// 调用service
		String mesg = jobService.publishJob(job);
		System.out.println("请求到upload:   " + deadTime);
		System.out.println("file    :" + file);
		Map<String, Object> map = new HashMap<>();
		if (mesg == null)
			map.put("success", "success");
		else
			map.put("success", "failed");
		return JSON.toJSONString(map);
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	@ResponseBody
	public String refresh(String title) {
		return jobService.queryJobList(title);
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void fileDownload(@RequestParam("fileName") String fileName, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 对文件名进行处理分离处理

		System.out.println("fileName  :" + fileName);
		String filePath = request.getSession().getServletContext().getRealPath("/WEB-INF/teacherupload");
		DownloadService.download(fileName, filePath, request, response);
	}
	
	@RequestMapping(value="/mutiDownload",method=RequestMethod.GET)
	public void mutiDownload(@RequestParam("fileNames") String fileNames,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		System.out.println("controller fileNames"+fileNames);
		String filePath=request.getSession().getServletContext().getRealPath("/WEB-INF/teacherupload");
		DownloadService.mutiDownload(fileNames, filePath, request, response);
	}

	@RequestMapping(value = "/pageHelper", method = RequestMethod.GET)
	@ResponseBody
	public void pageHelper(@RequestParam("page") String currentPageNum, @RequestParam("rows") String pageSize) {
		System.out.println("currentPageNum: " + currentPageNum);
		System.out.println("pageSize:  " + pageSize);
		// return null;
	}
}
