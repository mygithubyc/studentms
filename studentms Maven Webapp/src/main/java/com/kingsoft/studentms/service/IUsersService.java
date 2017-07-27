package com.kingsoft.studentms.service;

import com.kingsoft.studentms.model.Users;

public interface IUsersService {

	/**
	 * @用户登录
	 * @param username
	 * @param password
	 * @return Users对象
	 */
	public Users login(String username,String password);
	/**
	 * @用户注册检查
	 * @param username
	 * @return Users
	 */
	public boolean cheackRegister(String username);
	/**
	 * @注册ע
	 * @param username
	 * @param password
	 * @return String
	 */
	public String register(String username,String password);
	
}
