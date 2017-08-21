package com.kingsoft.studentms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kingsoft.studentms.basic.ConstantUtil;
import com.kingsoft.studentms.model.UserInfo;

/**
 * @登录拦截
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("preHandle: path=" + request.getProtocol() + request.getServerName() + ":/"
				+ request.getServerPort() + request.getContextPath());
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
		if (userInfo == null) {
			System.out.println("userinfo " + userInfo);
			// http://localhost:8080/studentms
			// String url=request.getContextPath();
			// response.sendRedirect("http://localhost:8080/studentms"); //调回初始页
			response.sendRedirect(request.getServerName() + ":/" + request.getServerPort() + request.getContextPath());
			// response.sendError(0, "请你先登录");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		// 登录成功后不同身份的用户访问不同的资源 获得session

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
		if (userInfo.getUserType().equals(ConstantUtil.USERTYPE_TEACHER)) {
			// 展示老师的静态资源

		} else if (userInfo.getUserType().equals(ConstantUtil.USERTYPE_STUDENT)) {
			// 展示学生的静态资源

		} else { // 如果是管理员返回登录界面，不让访问老师或者学生的资源,回到登录界面
			response.sendRedirect("http://localhost:8080/studentms");
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
