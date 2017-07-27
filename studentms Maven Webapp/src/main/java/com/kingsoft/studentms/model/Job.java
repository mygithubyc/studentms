package com.kingsoft.studentms.model;

import java.util.Date;

public class Job {

	private int jid;
	private String title;
	private String content;
	private Date sendTime;
	private String username;	//老师用户名
	private String path;
	private Date deadTime;
	public int getJid() {
		return jid;
	}
	public void setJid(int jid) {
		this.jid = jid;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getDeadTime() {
		return deadTime;
	}
	public void setDeadTime(Date deadTime) {
		this.deadTime = deadTime;
	}
	public Job(){}
	public Job(String content, Date sendTime, String username, String path, Date deadTime) {
		this.content = content;
		this.sendTime = sendTime;
		this.username = username;
		this.path = path;
		this.deadTime = deadTime;
	}
}
