package com.kingsoft.studentms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kingsoft.studentms.model.UserInfo;

public interface IUserInfoDao extends IBasicDao {

	/**
	 * @登录
	 * @param username
	 * @param password
	 * @return users
	 */
	public UserInfo login(@Param("username") String username, @Param("password") String password,@Param("userType") String userType);

	/**
	 * @批量导入用户信息
	 * @param userList
	 * @return
	 */
	public int addForeach(@Param("userList") List<?> userList);

	/**
	 * @添加用户查询
	 * @param username
	 * @return object
	 */
	public UserInfo addCheack(@Param("username") String username);
}
