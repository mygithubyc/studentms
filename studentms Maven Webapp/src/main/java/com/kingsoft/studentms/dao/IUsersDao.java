package com.kingsoft.studentms.dao;

import org.apache.ibatis.annotations.Param;

import com.kingsoft.studentms.model.Users;

public interface IUsersDao {
	
	
	public Users getUserByUsername(String username,String userType);
	
	
	/**
	 * @登录
	 * @param username
	 * @param password
	 * @return users
	 */
	public Users login(@Param("username") String username, @Param("password") String password);

	/**
	 * @注册检查
	 * @param username
	 * @return
	 */
	public Users cheackRegister(@Param("username") String username);

	/**
	 * @注册
	 * @param username
	 * @param password
	 * @return int
	 */
	public int register(@Param("user") Users user);
}
