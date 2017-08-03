package com.kingsoft.studentms.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kingsoft.studentms.model.ComJob;
@Repository
public interface IComJobDao {

	public int selectComjobCount(String username);
	
	
	
	
	
	
	
	
	/**
	 * @�ύ��ҵ
	 * @param comJob
	 * @return
	 */
	public int comJob(@Param("comJob") ComJob comJob);
}
