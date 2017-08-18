package com.kingsoft.studentms.dao;

import com.kingsoft.studentms.model.Manager;

public interface IManagerDao {

	/**
	 * @添加管理员
	 * @param manager
	 * @return
	 */
	public int addManager(Manager manager);
}
