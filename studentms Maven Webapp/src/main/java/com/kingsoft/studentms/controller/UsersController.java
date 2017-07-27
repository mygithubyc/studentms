package com.kingsoft.studentms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

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
		Users user = this.usersService.login(username, password);
		if (user != null) { // 验证通过
			modelMap.addAttribute("user", user); // 将用户信息保存到session里面
			// 视图配置
			modelView = new ModelAndView("view/welcome");
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
	public ModelAndView register(String username, String password) {
		System.out.println("请求到register username"+username);
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
	 * @前往注册界面
	 * @return viewPath
	 */
	@RequestMapping("/goRegister")
	public String goRegister() {
		return "register";
	}
	@RequestMapping("/goMain")
	public String goMain(){
		return "main/main";
	}
}
