package com.kingsoft.studentms.model;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.sun.jmx.snmp.Timestamp;

public class DownloadRecord {
	public static final int STATUS_ERROR = 0;
	public static final int STATUS_SUCCESS = 1;
	private String uid;
	private String ip;
	private int port;
	private String ua;
	private String fileName;
	private String filePath;
	private long length;
	private int status;
	private Timestamp startTime;
	private Timestamp endTime;
	
	public DownloadRecord(String fileName,String filePath,HttpServletRequest request){
		this.uid = UUID.randomUUID().toString().replace("-", "");
		this.fileName = fileName;
		this.filePath = filePath;
		this.ip = request.getRemoteAddr();
		this.port = request.getRemotePort();
		this.ua = request.getHeader("user-agent");
		this.startTime = new Timestamp(System.currentTimeMillis());
	}
	
	public String getUid(){
		return this.uid;
	}
	public void setUid(String uid){
		this.uid = uid;
	}
	public String getIp(){
		return this.ip;
	}
	public void setIp(String ip){
		this.ip = ip;
	}
	public String getUa(){
		return this.ua;
	}
	public void setUa(String ua){
		this.ua = ua;
	}
	public String getFileName(){
		return this.fileName;
	}
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	public String getFilePath(){
		return this.filePath;
	}
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}
	public int getPort(){
		return this.port;
	}
	public void setPort(int port){
		this.port = port;
	}
	public int getStatus(){
		return this.status;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public long getLength(){
		return this.length;
	}
	public void setLength(long length){
		this.length = length;
	}
	public Timestamp getStartTime(){
		return this.startTime;
	}
	public void setStartTime(Timestamp startTime){
		this.startTime = startTime;
	}
	public Timestamp getEndTime(){
		return this.endTime;
	}
	public void setEndTime(Timestamp endTime){
		this.endTime = endTime;
	}
}
