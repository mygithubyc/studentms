package com.kingsoft.studentms.dao;

import java.util.List;

import com.kingsoft.studentms.model.LoginInfo;

public interface ILoginInfoDao extends IBasicDao{

	/**
	 * @登录信息
	 * @param loginInfo
	 * @return
	 */
	public int addLoginInfo(LoginInfo loginInfo);
	
	/**
	 * @查询登录用户的登录信息
	 * @param object	可以按登录时间查询，用户名查询	
	 * @return
	 */
	public List<?> getLoginList(Object object);
	
}
