package com.kingsoft.studentms.controller;

import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.kingsoft.studentms.model.Job;
import com.kingsoft.studentms.model.UserInfo;
import com.kingsoft.studentms.service.ICommitInfoService;
import com.kingsoft.studentms.service.IJobService;
import com.kingsoft.studentms.service.IUserInfoService;

@Controller // 注入controller
@RequestMapping("/users") // 注入并配置controller总开关
@SessionAttributes("user") // 注入session的键
public class UsersController {

	public UsersController() {
		System.out.println("UsersController实例化");
	}

	// private ModelAndView modelView; // 返回一个model视图
	@Resource
	private IUserInfoService userInfoService; // 注入service
	@Resource
	private IJobService jobService;
	@Resource
	private ICommitInfoService commitInfoService;
	private Job job;

	private ModelAndView modelAndView;
	/*
	 * @RequestMapping(value="/goMain") public String goMain(String n){
	 * System.out.println("n "+n); return "common/main"; }
	 */

	/**
	 * @登录验证
	 * @param username
	 * @param password
	 * @param modelMap
	 *            保存session使用
	 * @return model视图
	 */
	// 配置分路由支路
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(String username, String password, String type, ModelMap modelMap) {
		System.out.println("login username:  " + username);

		System.out.println("username " + username + "  " + type);
		UserInfo user = this.userInfoService.login(username, password, type);
		System.out.println("user " + user);

		modelMap.addAttribute("user", user);
		Map<String, Object> map = new HashMap<>();
		/*
		 * if (user != null) { // 验证通过 System.out.println("user " + user);
		 * modelMap.addAttribute("user", user); // 将用户信息保存到session里面 modelView
		 * map.put("success", "success"); } else { map.put("success", "failed");
		 * }
		 * 
		 * return JSON.toJSONString(map);
		 */
		modelAndView = new ModelAndView("common/main");
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	/**
	 * @Excel文件导入数据库操作
	 * @param file
	 * @throws IOException
	 */
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	@ResponseBody
	public String importExcel(@RequestParam MultipartFile file) throws IOException {
		System.out.println("userController Excel上传！");

		Map<String, Object> map = new HashMap<>();
		if (file.isEmpty()) {
			System.out.println("文件不能为空");
			map.put("success", "文件不能为空");
			return JSON.toJSONString(map);
		}
		InputStream inputStream = file.getInputStream(); // 得到文件输入流
		userInfoService.importExcel(inputStream, file);
		// 返回json数据
		map.put("success", "success");
		return JSON.toJSONString(map);
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
