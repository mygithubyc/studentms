package com.kingsoft.studentms.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
	
	private static final String UPLAOD_DIRCTORY = "WEB-INF/download";
	//上传配置
	private static final int MEMORY_THRESHOLD = 1024 * 1024 *3;
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;
	
	
	
	
	
	@RequestMapping("/getJobs")
	public void getJobs(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String rows = request.getParameter("rows") != null ? (String) request.getParameter("rows") : "10";
		String page = request.getParameter("page") != null ? (String) request.getParameter("page") : "1";
		String sort = request.getParameter("sort") != null ? (String)request.getParameter("sort") : "jid";
		String order = request.getParameter("order") != null ? (String)request.getParameter("order") : "asc";
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("user");
		
		int offset = Integer.parseInt(rows)*(Integer.parseInt(page)-1);
		int limit =  offset+Integer.parseInt(rows);
		String json = "";
		PrintWriter pw = response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");

		
		int total = jobService.getJobCount(users.getUsername());

		java.util.Map<String, Object> selectMap = new HashMap<String, Object>();
		selectMap.put("offset", offset);
		selectMap.put("limit", limit);
		selectMap.put("order", order);
		selectMap.put("sort", sort);
		selectMap.put("username", users.getUsername());
		List<Job> jobs = jobService.selectJobByUsername(selectMap);
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", jobs);
		json  = JSON.toJSONString(map);

//		json =JSON.toJSONString(rows+"/"+page+"/"+sort+"/"+order);
		pw.write(json);
		
	}

	@RequestMapping("/addJob")
	public void addJob(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		
		PrintWriter pw = response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		

		if (!ServletFileUpload.isMultipartContent(request)) {			
			pw.println("{\"msg\": \"表单必须包含 多媒体上传\"}");
			pw.flush();
			return ;
		}
		
		HttpSession session = request.getSession();
		
		//配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存临界值
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// 设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);
		
		// 设置最大请求值(包含文件和表单)
		upload.setSizeMax(MAX_REQUEST_SIZE);
		
		//中文处理
		upload.setHeaderEncoding("utf-8");
		
		// 构造临时路径来存储上传的文件
		// 这个路径相对当前应用的目录
		String uploadPath = session.getServletContext().getRealPath("") + File.separator + UPLAOD_DIRCTORY;
		
		//如果不存在则创建
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			System.out.println("创建download文件夹"+uploadPath);
			uploadDir.mkdir();
		}
		
		
		
		
//		String title = (String) request.getParameter("title");
//		String content = (String) request.getParameter("content");
//		String deadtime = (String) request.getParameter("deadtime");
//		String path = (String) request.getParameter("path");
		
		System.out.println(uploadPath);
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
