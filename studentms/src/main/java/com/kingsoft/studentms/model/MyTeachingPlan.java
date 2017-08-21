package com.kingsoft.studentms.model;

public class MyTeachingPlan {
	private int courseId;
	private String courseName;
	private int term;
	private String schoolName;
	
	
	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
}
