package com.kingsoft.studentms.model;

public class Class {

	private int classId;
	private int teacherId; // 班主任
	private int departId;
	private String className;
	private String classStatus;

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getDepartId() {
		return departId;
	}

	public void setDepartId(int departId) {
		this.departId = departId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassStatus() {
		return classStatus;
	}

	public void setClassStatus(String classStatus) {
		this.classStatus = classStatus;
	}

}
