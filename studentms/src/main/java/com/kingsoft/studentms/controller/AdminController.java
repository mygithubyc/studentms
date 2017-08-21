package com.kingsoft.studentms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Constants;
import com.kingsoft.studentms.model.UserInfo;
import com.kingsoft.studentms.service.IUserInfoService;

@Controller
@RequestMapping("/admin")
@SessionAttributes("user")
public class AdminController {

	private UserInfo userInfo;
	@Resource
	private IUserInfoService userInfoService;

	// 显示管理员登录界面
	@RequestMapping()
	public String index() {
		System.out.println("admin()");
		return "admin/login";
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {
		System.out.println("main()");
		return "admin/main/main";
	}
	@RequestMapping(value = "/loginInfo", method = RequestMethod.GET)
	public String loginInfo() {
		System.out.println("loginInfo()");
		return "admin/loginInfo/show";
	}
	@RequestMapping(value = "/exam", method = RequestMethod.GET)
	public String exam() {
		System.out.println("exam()");
		return "admin/exam/show";
	}
	// 点击登录按钮操作
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(String username, String password, String captcha, ModelMap modelMap,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		String k = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (captcha.equals(k)) {
			UserInfo userInfo = userInfoService.login(username, password,"1");
			if (userInfo != null) {
				// 保存session
				modelMap.addAttribute("user", userInfo);
				map.put("success", true);
				return map;
			} else {
				map.put("msg", "用户名密码错误");
				return map;
			}
		} else {
			map.put("msg", "验证码有误");
			return map;
		}
	}

	@RequestMapping(value = "/addSingle", method = RequestMethod.POST)
	@ResponseBody
	public String addSingle(String username, String password, String sex, String userType) {

		// 装配对象
		userInfo = new UserInfo();
		userInfo.setUsername(username);
		userInfo.setPassword(password);

		// 性别转换
		if (sex.trim().equals("男"))
			userInfo.setSex("1");
		else
			userInfo.setSex("2");

		// 身份类型转换
		if (userType.trim().equals("管理员"))
			userInfo.setUserType("1");
		else if (userType.trim().equals("老师"))
			userInfo.setUserType("2");
		else
			userInfo.setUserType("3");

		Map<String, Object> map = new HashMap<>();
		String message = userInfoService.addSingle(userInfo);
		if (message == null) {
			map.put("success", "操作成功");
			return JSON.toJSONString(map);
		} else {
			map.put("success", message);
			return JSON.toJSONString(map);
		}
	}
}
