package com.kingsoft.studentms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ICommitInfoDao extends IBasicDao{

	/**
	 * @得到所有的提交记录
	 * @return
	 */
	public List<?> getCommitList(@Param("publishId")int publishId);
	
	
}
