package com.kingsoft.studentms.model;

public class CourseAssign {

	private int courseAssignId;
	private int departId;
	private int courseId;
	private String assignStatus;
	private String term;

	public int getCourseAssignId() {
		return courseAssignId;
	}

	public void setCourseAssignId(int courseAssignId) {
		this.courseAssignId = courseAssignId;
	}

	public int getDepartId() {
		return departId;
	}

	public void setDepartId(int departId) {
		this.departId = departId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getAssignStatus() {
		return assignStatus;
	}

	public void setAssignStatus(String assignStatus) {
		this.assignStatus = assignStatus;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

}
