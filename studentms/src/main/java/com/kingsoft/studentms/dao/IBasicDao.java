package com.kingsoft.studentms.dao;

public interface IBasicDao {

	/**
	 * @添加对象
	 * @param object
	 * @return 
	 */
	public int add(Object object);
	
	/**
	 * @修改方法
	 * @param object
	 * @return 
	 */
	public int update(Object object);
}
