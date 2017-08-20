package com.kingsoft.studentms.basic;

import java.io.Serializable;

/**
 * @分页一个工具类
 * @author Administrator
 *
 */
public class PageUtil implements Serializable {

	/**
	 * @分页属性
	 */
	private static final long serialVersionUID = 1L;
	private int currentPageNum;	
	private int pageSize;
	
	public int getCurrentPageNum() {
		return currentPageNum;
	}
	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
