package com.kingsoft.studentms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.kingsoft.studentms.model.Job;
import com.kingsoft.studentms.model.Users;
import com.kingsoft.studentms.service.IJobService;
import com.sun.javafx.collections.MappingChange.Map;

@Controller
@RequestMapping("/job")
public class JobController {

	@Resource
	private IJobService jobService;
	
	@RequestMapping("/getJobs")
	public void getJobs(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String rows = request.getParameter("rows") != null ? (String) request.getParameter("rows") : "10";
		String page = request.getParameter("page") != null ? (String) request.getParameter("page") : "1";
		String sort = request.getParameter("sort") != null ? (String)request.getParameter("sort") : "jid";
		String order = request.getParameter("order") != null ? (String)request.getParameter("order") : "asc";
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("user");
		
		
		
		String json = "";
		PrintWriter pw = response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		
		
		
//		int total = jobService.getJobCount("admin1");
		int total = 11;
//		ArrayList<Job> jobs = new ArrayList<Job>();
//		Job job = new Job("test","test",new Date(),"admin1","xxx.path",new Date());
//		job.setJid(1);
//		jobs.add(job);
		List<Job> jobs = jobService.selectJobByUsername("test");
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", jobs);
		json  = JSON.toJSONString(map);

//		json =JSON.toJSONString(rows+"/"+page+"/"+sort+"/"+order);
		pw.write(json);
		
	}
	
	
	@RequestMapping("/student")
	public String student(){
		return "homework/student";
	}
	@RequestMapping("/teacher")
	public String teacher(){
		return "homework/teacher";
	}
}
