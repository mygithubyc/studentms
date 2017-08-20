package com.kingsoft.studentms.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
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
import com.kingsoft.studentms.model.UserInfo;
import com.kingsoft.studentms.service.IUserInfoService;

@Controller // 注入controller
@RequestMapping("/users") // 注入并配置controller总开关
@SessionAttributes("user") // 注入session的键

public class UsersController {

	public UsersController() {
		System.out.println("UsersController实例化");
	}

	private ModelAndView modelView; // 返回一个model视图
	@Resource
	private IUserInfoService userInfoService; // 注入service

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
	public ModelAndView login(String username, String password, String userType, ModelMap modelMap) {
		System.out.println("login username:  " + username);

		if (userType.equals("老师"))
			userType = "2";
		else
			userType = "3";	//学生
		UserInfo user = this.userInfoService.login(username, password, userType);
		if (user != null) { // 验证通过
			modelMap.addAttribute("user", user); // 将用户信息保存到session里面
			// 视图配置
			if (user.getUserType().equals("2"))
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
}
