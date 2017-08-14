package com.kingsoft.studentms.service;

import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.kingsoft.studentms.model.Users;

public interface IUsersService {

	/**
	 * @用户登录
	 * @param username
	 * @param password
	 * @return Users对象
	 */
	public Users login(String username, String password);

	/**
	 * @用户注册检查
	 * @param username
	 * @return Users
	 */
	public boolean cheackRegister(String username);

	/**
	 * @注册
	 * @param username
	 * @param password
	 * @return String
	 */
	public String register(String username, String password);

	/**
	 * 
	 * @param inputStream
	 * @param multipartFile
	 */
	public void importExcel(InputStream inputStream, MultipartFile multipartFile);

	/**
	 * 
	 * @param object
	 * @return
	 */
	public XSSFWorkbook exportExcel(String userType);

}
