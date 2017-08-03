package com.kingsoft.studentms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.kingsoft.studentms.model.Users;
import com.kingsoft.studentms.service.IComJobService;

@Controller
@RequestMapping("/comJob")
public class ComJobController {

	@Resource
	private IComJobService comJobService;
	
	
	@RequestMapping("/getComJob")
	public void getComJob(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String rows = request.getParameter("rows") != null ? (String) request.getParameter("rows") : "10";
		String page = request.getParameter("page") != null ? (String) request.getParameter("page") : "1";
		String sort = request.getParameter("sort") != null ? (String)request.getParameter("sort") : "cid";
		String order = request.getParameter("order") != null ? (String)request.getParameter("order") : "asc";
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("user");
		
		
		int offset = Integer.parseInt(rows)*(Integer.parseInt(page)-1);
		int limit =  offset+Integer.parseInt(rows);
		String json = "";
		PrintWriter pw = response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		
		int total = comJobService.selectComjobCount("admin1");
		
		
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
//		map.put("rows", jobs);
//		map.put("test", users.getUsername());
		json  = JSON.toJSONString(map);
		
		pw.write(json);

		
	}
	
	
	
	
	
	
	
	@RequestMapping("/teacher")
	public String teacher(){
		return "comjob/teacher";
	}
}
