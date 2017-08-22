package com.kingsoft.studentms.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.kingsoft.studentms.model.Class;
import com.kingsoft.studentms.model.MyDTeacher;

public interface IClassService {
	public List<Class> dClassCombobox(String departId);
	
	public List<MyDTeacher> dTeacherCombobox();
	public List<MyDTeacher> dNotTeacherCombobox();
	
	public Map<String, Object> addTeachingPlan(Map<String, Object> map) throws ParseException;
	
	public boolean dAddStudent(Map<String, Object> map,Map<String, Object> stuMap);
	public boolean dAddTeacher(Map<String, Object> map,Map<String, Object> teachMap);
	public boolean dAddClass(Class class1);
}
