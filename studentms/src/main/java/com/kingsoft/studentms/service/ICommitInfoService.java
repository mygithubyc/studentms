package com.kingsoft.studentms.service;

public interface ICommitInfoService {

	/**
	 * @通过ID获得提交记录
	 * @param publishId
	 * @return
	 */
	public String getCommitByPid(String publishId);
}
