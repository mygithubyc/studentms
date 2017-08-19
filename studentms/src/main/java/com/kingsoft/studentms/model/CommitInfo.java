package com.kingsoft.studentms.model;

import java.util.Date;

public class CommitInfo {

	private int commitId;
	private int publishId;
	private String stuUsername;
	private String commitPath;
	private Date commitLast;

	public int getCommitId() {
		return commitId;
	}

	public void setCommitId(int commitId) {
		this.commitId = commitId;
	}

	public int getPublishId() {
		return publishId;
	}

	public void setPublishId(int publishId) {
		this.publishId = publishId;
	}

	public String getStuUsername() {
		return stuUsername;
	}

	public void setStuUsername(String stuUsername) {
		this.stuUsername = stuUsername;
	}

	public String getCommitPath() {
		return commitPath;
	}

	public void setCommitPath(String commitPath) {
		this.commitPath = commitPath;
	}

	public Date getCommitLast() {
		return commitLast;
	}

	public void setCommitLast(Date commitLast) {
		this.commitLast = commitLast;
	}

}
