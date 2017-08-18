package com.kingsoft.studentms.service;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.kingsoft.studentms.model.UserInfo;

public interface IUserInfoService {

	/**
	 * @登录检查
	 * @param username
	 * @param password
	 * @return
	 */
	public UserInfo login(String username, String password);

	/**
	 * @单用户加入
	 * @param userInfo
	 * @return
	 */
	public String addSingle(UserInfo userInfo);

	/**
	 * @多用户加入
	 * @param inputStream
	 * @param multipartFile
	 */
	public String importExcel(InputStream inputStream, MultipartFile multipartFile);
}
