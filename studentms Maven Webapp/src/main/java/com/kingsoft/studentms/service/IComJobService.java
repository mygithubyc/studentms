package com.kingsoft.studentms.service;

import com.kingsoft.studentms.model.ComJob;

public interface IComJobService {

	
	public int selectComjobCount(String username);
	
	
	
	
	
	
	/**
	 * @�ύ��ҵ
	 * @param comJob
	 * @return string
	 */
	public String comJob(ComJob comJob);
	
}
