package com.kingsoft.studentms.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.kingsoft.studentms.model.Job;
import com.kingsoft.studentms.model.Users;
import com.kingsoft.studentms.service.IJobService;
import com.sun.javafx.collections.MappingChange.Map;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Controller
@RequestMapping("/job")
public class JobController {

	@Resource
	private IJobService jobService;
	
	private static final String UPLAOD_DIRCTORY = "WEB-INF/tecUpload";
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
	public void addJob(String title,String content,String deadtime,HttpServletRequest request,HttpServletResponse response,@RequestParam MultipartFile path) throws IOException, ParseException{
		
		
		PrintWriter pw = response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		
		if (!ServletFileUpload.isMultipartContent(request)) {			
			pw.println("{\"msg\": \"表单必须包含 多媒体上传\"}");
			pw.flush();
			return ;
		}else{
			System.out.println("多媒体上传");
		}

		
		HttpSession session = request.getSession();
		// 这个路径相对当前应用的目录
		String uploadPath = session.getServletContext().getRealPath("") + File.separator + UPLAOD_DIRCTORY;
		Users users = (Users) session.getAttribute("user");
		//建立存放的目录
		File uploadFile = new File(uploadPath);
		if (!uploadFile.exists()) {
			uploadFile.mkdir();
		}
		Date date = null;
		date = new SimpleDateFormat("MM/dd/yyyy").parse(deadtime);
		
		
		Job job = new Job();
		job.setTitle(title);
		job.setContent(content);
		job.setDeadTime(date);
		job.setUsername(users.getUsername());
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("job", job);
		map.put("path", uploadPath);
		map.put("file", path);

		
		String json = jobService.addJob(map);
		
		System.out.println(json);
		pw.write(json);
	}
	
	@RequestMapping("deleteJob")
	public void deleteJob(String jid,HttpServletResponse response) throws IOException{
		PrintWriter pw = response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		String json = "";
		if (jid != null) {
			System.out.println(jid);
			jobService.deleteByPrimaryKey(jid);
			json = "{\"success\":\"true\"}";
		}else {
			json = "{\"msg\":\"发生错误\"}";
		}
		
		pw.write(json);
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping("stuGetJob")
	public void stuGetJob(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		String rows = request.getParameter("rows") != null ? (String) request.getParameter("rows") : "10";
		String page = request.getParameter("page") != null ? (String) request.getParameter("page") : "1";
		String sort = request.getParameter("sort") != null ? (String)request.getParameter("sort") : "jid";
		String order = request.getParameter("order") != null ? (String)request.getParameter("order") : "asc";
		String username = request.getParameter("username") != null ? (String)request.getParameter("username") : "";
		String formDateString = request.getParameter("formDate") != null ? (String)request.getParameter("order") : "01/01/1900";
		String toDateString = request.getParameter("toDate") != null ? (String)request.getParameter("toDate") : "12/01/2050";
		HttpSession session = request.getSession();
		String cUsername = ((Users) session.getAttribute("user")).getUsername();
		
		Date formDate = new SimpleDateFormat("MM/dd/yyyy").parse(formDateString);
		Date toDate = new SimpleDateFormat("MM/dd/yyyy").parse(toDateString);
		int offset = Integer.parseInt(rows)*(Integer.parseInt(page)-1);
		int limit =  offset+Integer.parseInt(rows);
		String json = "";
		PrintWriter pw = response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		
		java.util.Map<String, Object> countMap = new HashMap<String, Object>();
		countMap.put("username", username);
		countMap.put("formDate",formDate);
		countMap.put("toDate",  toDate);
		int total = jobService.getAllJobCount(countMap);
		
		java.util.Map<String, Object> selectMap = new HashMap<String, Object>();
		selectMap.put("offset", offset);
		selectMap.put("limit", limit);
		selectMap.put("order", order);
		selectMap.put("sort", sort);
		selectMap.put("username", username);
		selectMap.put("formDate",formDate);
		selectMap.put("toDate",  toDate);
		selectMap.put("cUsername", cUsername);
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
//		map.put("rows", jobs);
		json = JSON.toJSONString(map);
		
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
