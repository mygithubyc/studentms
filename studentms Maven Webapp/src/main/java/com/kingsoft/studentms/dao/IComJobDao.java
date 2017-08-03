package com.kingsoft.studentms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kingsoft.studentms.model.ComJob;
import com.kingsoft.studentms.model.Submit;
@Repository
public interface IComJobDao {

	public int selectComjobCount(Map<String, Object> countMap);
	
	public List<Submit> selectComjob(Map<String, Object> map);
	
	
	
	
	
	
	/**
	 * @�ύ��ҵ
	 * @param comJob
	 * @return
	 */
	public int comJob(@Param("comJob") ComJob comJob);
}
