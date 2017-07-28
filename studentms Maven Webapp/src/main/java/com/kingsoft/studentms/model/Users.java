package com.kingsoft.studentms.model;

public class Users {

	private int usid;
	private String username;
	private String password;
	private String name;
	private String sex;
	private String phonenum;
	private String email;
	private String userType;
	public int getUsid() {
		return usid;
	}
	public void setUsid(int usid) {
		this.usid = usid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsertType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	/**
	 * @生成构造方法
	 */
	public Users(){
		
	}
	/**
	 * @生成构方法
	 * @param username 用户名
	 * @param password 密码
	 */
	public Users(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
