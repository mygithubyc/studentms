package com.kingsoft.studentms.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TestInterceptor implements HandlerInterceptor{

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("执行到了afterCompletion方法");
//		类似析构 关闭资源等 不常用
		

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO 自动生成的方法存根
		System.out.println("执行到了postHandle方法");
//		可以通过modelAndView方法来改变显示的视图 或修改发往视图的方法
//		arg3.addObject("msg", "这里是被拦截器修改之后的消息");
//		arg3.setViewName("/index");
	}
// 是否需要将当前请求拦截下来 返回false终止 返回true进行
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		arg0.setCharacterEncoding("utf-8");
		if(arg0.getSession().getAttribute("user")==null){
			arg0.getRequestDispatcher("/index.jsp").forward(arg0, arg1);
			return false;
		}
		System.out.println("执行到了preHandle方法");
		return true;
	}

}
