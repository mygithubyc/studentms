package com.kingsoft.studentms.model;

import java.util.Date;

public class ComJob {

	private int cid;
	private String username;
	private Date uploadTime;
	private String path;		//�ύ��ҵ·��
	private int jid;
	private String struts;
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getJid() {
		return jid;
	}
	public void setJid(int jid) {
		this.jid = jid;
	}
	public String getStruts() {
		return struts;
	}
	public void setStruts(String struts) {
		this.struts = struts;
	}
	public ComJob(){}
	public ComJob(String username, Date uploadTime, String path, int jid, String struts) {
		this.username = username;
		this.uploadTime = uploadTime;
		this.path = path;
		this.jid = jid;
		this.struts = struts;
	}
	
}
