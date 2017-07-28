package com.kingsoft.studentms.dao;

import org.apache.ibatis.annotations.Param;

import com.kingsoft.studentms.model.Users;

public interface IUsersDao {
	
	
	public Users getUserByUsername(@Param("username")String username,@Param("userType")String userType);
		
	
	
	
	
	
	
	
	
	
//	杨超写
	public Users login(@Param("username") String username, @Param("password") String password);
	public Users cheackRegister(@Param("username") String username);
	public int register(@Param("user") Users user);
}
