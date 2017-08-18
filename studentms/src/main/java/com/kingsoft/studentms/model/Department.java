package com.kingsoft.studentms.model;

public class Department {

	private int departId;
	private int schoolId;
	private String departName;
	private String departStatus; // 值范围（'0','1'）,1表示系存在，0表示不存在

	public int getDepartId() {
		return departId;
	}

	public void setDepartId(int departId) {
		this.departId = departId;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getDepartStatus() {
		return departStatus;
	}

	public void setDepartStatus(String departStatus) {
		this.departStatus = departStatus;
	}

}
