package com.kingsoft.studentms.service;

import java.util.List;

import com.kingsoft.studentms.model.Class;
import com.kingsoft.studentms.model.MyDTeacher;

public interface IClassService {
	public List<Class> dClassCombobox(String departId);
	
	public List<MyDTeacher> dTeacherCombobox();
}
