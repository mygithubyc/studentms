package com.kingsoft.studentms.model;

import java.util.Date;

public class LoginInfo {

	private int loginId;
	private String loginUsername;
	private String ip;
	private Date loginTime;
	private Date logoutTime;
	private String mac;
	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getMac() {
		return mac;
	}
}
