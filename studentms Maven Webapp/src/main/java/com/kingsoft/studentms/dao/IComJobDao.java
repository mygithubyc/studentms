package com.kingsoft.studentms.dao;

import org.apache.ibatis.annotations.Param;

import com.kingsoft.studentms.model.ComJob;

public interface IComJobDao {

	/**
	 * @�ύ��ҵ
	 * @param comJob
	 * @return
	 */
	public int comJob(@Param("comJob") ComJob comJob);
}
