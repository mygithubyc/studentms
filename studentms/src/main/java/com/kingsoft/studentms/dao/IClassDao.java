package com.kingsoft.studentms.dao;

import java.util.List;

import com.kingsoft.studentms.model.Class;
import com.kingsoft.studentms.model.MyDTeacher;

public interface IClassDao extends IBasicDao{
	public List<Class> dClassCombobox(String departId);
	
	public List<MyDTeacher> dTeacherCombobox();
	
	public List<MyDTeacher> dNotTeacherCombobox();
	
	public Class dGetClass(String classId);
	
	public int dAddClass(Class class1);
}
