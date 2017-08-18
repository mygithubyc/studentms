package com.kingsoft.studentms.basic;

import java.io.Serializable;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;

public class ExcelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String headTextName; // 标题名
	private String propertyName; // 对应字段名
	private Integer cols; // 合并单元格数
	private XSSFCellStyle cellStyle;

	public String getHeadTextName() {
		return headTextName;
	}

	public void setHeadTextName(String headTextName) {
		this.headTextName = headTextName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Integer getCols() {
		return cols;
	}

	public void setCols(Integer cols) {
		this.cols = cols;
	}

	public XSSFCellStyle getCellStyle() {
		return cellStyle;
	}

	public void setCellStyle(XSSFCellStyle cellStyle) {
		this.cellStyle = cellStyle;
	}

	// 构造方法
	public ExcelBean() {
	}

	/**
	 * @构造二参数构造方法
	 * @param headTextName
	 * @param propertyNmae
	 */
	public ExcelBean(String headTextName, String propertyNmae) {
		this.headTextName = headTextName;
		this.propertyName = propertyNmae;
	}

	/**
	 * @三参数构造方法
	 * @param headTextName
	 * @param propertyNmae
	 * @param cols
	 */
	public ExcelBean(String headTextName, String propertyNmae, Integer cols) {
		this.headTextName = headTextName;
		this.propertyName = propertyNmae;
		this.cols = cols;
	}
}
