package com.kingsoft.studentms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.kingsoft.studentms.service.IJobService;

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
		
		String json = "";
		PrintWriter pw = response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		
//		String[] result = {rows};
		json =JSON.toJSONString(rows+"/"+page+"/"+sort+"/"+order);
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
