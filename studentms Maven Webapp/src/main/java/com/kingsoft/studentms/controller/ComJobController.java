package com.kingsoft.studentms.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.kingsoft.studentms.model.ComJob;
import com.kingsoft.studentms.model.Submit;
import com.kingsoft.studentms.model.Users;
import com.kingsoft.studentms.service.IComJobService;

@Controller
@RequestMapping("/comJob")
public class ComJobController {

	@Resource
	private IComJobService comJobService;
	
	private static final String STU_UPLAOD_DIRCTORY = "WEB-INF/stuUpload";
	
	@RequestMapping("/submitJob")
	public void submitJob(String jid,HttpServletRequest request,HttpServletResponse response,@RequestParam MultipartFile file) throws IOException{
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
		Users users = (Users) session.getAttribute("user");
//		上传前的检查  用户是否提交过!
		Map<String, Object> checkMap = new HashMap<String, Object>();
		checkMap.put("jid", jid);
		checkMap.put("username", users.getUsername());
		if (!comJobService.checkResubmit(checkMap)) {
			pw.write("{\"msg\":\"你已经提交过这次作业!\"}");
			return ;
		}
		
		
		// 这个路径相对当前应用的目录
		String uploadPath = session.getServletContext().getRealPath("") + File.separator + STU_UPLAOD_DIRCTORY;
		
		//建立存放的目录
		File uploadFile = new File(uploadPath);
		if (!uploadFile.exists()) {
			uploadFile.mkdir();
		}
		
		ComJob comJob = new ComJob();
		comJob.setUsername(users.getUsername());
		comJob.setUploadTime(new Date());
		comJob.setJid(Integer.parseInt(jid));
		comJob.setStruts("1");
		Map<String, Object> submitMap = new HashMap<String, Object>();
		submitMap.put("comJob", comJob);
		submitMap.put("file", file);
		submitMap.put("uploadPath", uploadPath);
		String json = comJobService.submitJob(submitMap);
		
//		String json = JSON.toJSONString(submitMap);
		
		pw.write(json);
		
	}
	
	@RequestMapping("/getComJob")
	public void getComJob(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String rows = request.getParameter("rows") != null ? (String) request.getParameter("rows") : "10";
		String page = request.getParameter("page") != null ? (String) request.getParameter("page") : "1";
		String sort = request.getParameter("sort") != null ? (String)request.getParameter("sort") : "cid";
		String order = request.getParameter("order") != null ? (String)request.getParameter("order") : "asc";
		String cUsername = request.getParameter("cUsername") != null ? (String)request.getParameter("cUsername") : "";
		String title = request.getParameter("title") != null ? (String)request.getParameter("title") : "";
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("user");
		
		
		int offset = Integer.parseInt(rows)*(Integer.parseInt(page)-1);
		int limit =  offset+Integer.parseInt(rows);
		String json = "";
		PrintWriter pw = response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		java.util.Map<String, Object> countMap = new HashMap<String, Object>();
		countMap.put("username", users.getUsername());
		countMap.put("cUsername", cUsername);
		countMap.put("title", title);
		int total = comJobService.selectComjobCount(countMap);
		
		
		
		
		java.util.Map<String, Object> selectMap = new HashMap<String, Object>();
		selectMap.put("offset", offset);
		selectMap.put("limit", limit);
		selectMap.put("order", order);
		selectMap.put("sort", sort);
		selectMap.put("username", users.getUsername());
		selectMap.put("cUsername", cUsername);
		selectMap.put("title", title);
		List<Submit> submits = comJobService.selectComjob(selectMap);
				
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", submits);
//		map.put("test", selectMap);
		json  = JSON.toJSONString(map);
		
		pw.write(json);

		
	}
	
	
	
	
	
	
	
	@RequestMapping("/teacher")
	public String teacher(){
		return "comjob/teacher";
	}
}
