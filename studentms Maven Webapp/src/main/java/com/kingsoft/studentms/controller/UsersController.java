package com.kingsoft.studentms.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.kingsoft.studentms.model.Users;
import com.kingsoft.studentms.service.IUsersService;

@Controller // 注入controller
@RequestMapping("/users") // 注入并配置controller总开关
@SessionAttributes("user") // 注入session的键

public class UsersController {

	public UsersController() {
		System.out.println("UsersController实例化");
	}

	private ModelAndView modelView; // 返回一个model视图
	@Resource
	private IUsersService usersService; // 注入service

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
	public ModelAndView login(String username, String password, ModelMap modelMap) {
		System.out.println("login username:  " + username);
		Users user = this.usersService.login(username, password);
		if (user != null) { // 验证通过
			modelMap.addAttribute("user", user); // 将用户信息保存到session里面
			// 视图配置
			if (user.getUsertType().equals("2"))
				modelView = new ModelAndView("homework/teacher");
			else
				modelView = new ModelAndView("homework/student");
			modelView.addObject("user", user);
			return modelView;
		} else {
			modelView = new ModelAndView("login");
			modelView.addObject("mesg", "账号或密码错误！");
			return modelView;
		}
	}

	/**
	 * @注册方法
	 * @param username
	 * @param password
	 * @return 返回对应的视图
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView register(String username, String password) {
		System.out.println("请求到register username：" + username);
		String regMesg = this.usersService.register(username, password);
		if (regMesg == null) {
			modelView = new ModelAndView("login");
			modelView.addObject("regMesg", "恭喜你注册成功！");
			return modelView;
		}
		modelView = new ModelAndView("register");
		modelView.addObject("regMesg", regMesg);
		return modelView; // 默认注册失败
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
		usersService.importExcel(inputStream, file);
		// 返回json数据
		map.put("success", "success");
		return JSON.toJSONString(map);
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public void exportExcel(@RequestParam("object") Object object, HttpServletResponse response) {

		System.out.println("userController exportExcel"+object);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("multipart/form-data");
		//response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;fileName=" + "userInfo.xlsx");

		XSSFWorkbook xssfWorkbook = null;
		xssfWorkbook = usersService.exportExcel("2");	//取出用户类型为2
		OutputStream outputStream;

		try {
			outputStream = response.getOutputStream();		//激活下载命令
			BufferedOutputStream buffOS = new BufferedOutputStream(outputStream);
			buffOS.flush();
			xssfWorkbook.write(buffOS);
			System.out.println("xssfWorkbook  "+xssfWorkbook);
			System.out.println("buffOS  "+buffOS);
			buffOS.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @前往注册界面
	 * @return viewPath
	 */
	@RequestMapping("/goRegister")
	public String goRegister() {
		return "register";
	}

	@RequestMapping("/goMain")
	public String goMain() {
		return "main/main";
	}
}
