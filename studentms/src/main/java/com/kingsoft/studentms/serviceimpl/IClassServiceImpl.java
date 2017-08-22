package com.kingsoft.studentms.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kingsoft.studentms.dao.IClassDao;
import com.kingsoft.studentms.model.Class;
import com.kingsoft.studentms.model.MyDTeacher;
import com.kingsoft.studentms.service.IClassService;
@Service("classService")
public class IClassServiceImpl implements IClassService{

	@Resource
	private IClassDao classDao;
	@Override
	public List<Class> dClassCombobox(String departId) {
		// TODO 自动生成的方法存根
		return classDao.dClassCombobox(departId);
	}
	@Override
	public List<MyDTeacher> dTeacherCombobox() {
		// TODO 自动生成的方法存根
		return classDao.dTeacherCombobox();
	}
	@Override
	public Map<String, Object> addTeachingPlan(Map<String, Object> addMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		String classId = (String) addMap.get("classId");
		
		Class class1 = classDao.dGetClass(classId);
//		int buildYear = class1.get
		
		
		return map;
	}
	
}
