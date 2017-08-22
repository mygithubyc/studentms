package com.kingsoft.studentms.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kingsoft.studentms.dao.IClassDao;
import com.kingsoft.studentms.dao.IStudentDao;
import com.kingsoft.studentms.dao.ITeacherDao;
import com.kingsoft.studentms.dao.ITeachingPlanDao;
import com.kingsoft.studentms.dao.IUserInfoDao;
import com.kingsoft.studentms.md5.EncodeMd5;
import com.kingsoft.studentms.model.Class;
import com.kingsoft.studentms.model.MyDTeacher;
import com.kingsoft.studentms.model.TeachingPlan;
import com.kingsoft.studentms.model.UserInfo;
import com.kingsoft.studentms.service.IClassService;
@Service("classService")
public class IClassServiceImpl implements IClassService{

	@Resource
	private IClassDao classDao;
	@Resource
	private ITeachingPlanDao teachingPlanDao;
	@Resource
	private IUserInfoDao userInfoDao;
	@Resource
	private ITeacherDao teacherDao;
	@Resource
	private IStudentDao studentDao;
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
	public Map<String, Object> addTeachingPlan(Map<String, Object> addMap) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		String classId = (String) addMap.get("classId");
		String term = (String) addMap.get("term");
		String courseId = (String) addMap.get("courseId");
		String teacherId = (String) addMap.get("teacherId");
		
		int termNum = Integer.parseInt(term);
		Class class1 = classDao.dGetClass(classId);
		int buildYear = class1.getBuildYear();
		String datetime = buildYear+"-09";
		System.out.println(datetime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
		Date date = null,
			 formDate = null,
			 toDate = null;  
        
        date = sdf.parse(datetime);  
        Calendar cl = Calendar.getInstance();  
        cl.setTime(date);  
        if (termNum == 1) {
        	formDate = cl.getTime();
		}else{
			cl.add(Calendar.MONTH, (termNum-1)*6);  
	        formDate = cl.getTime();
		}
        
		
        cl.add(Calendar.MONTH, 6);
        
        toDate = cl.getTime();
        System.out.println("开始时间"+formDate);
        System.out.println("结束时间"+toDate);

        TeachingPlan teachingPlan = new TeachingPlan();
        teachingPlan.setCourseId(Integer.parseInt(courseId));
        teachingPlan.setClassId(Integer.parseInt(classId));
        teachingPlan.setTeacherId(Integer.parseInt(teacherId));
        teachingPlan.setTermStart(formDate);
        teachingPlan.setTermEnd(toDate);
        int insert = teachingPlanDao.addTeachingPlan(teachingPlan);
        if(insert > 0){
        	map.put("success",true);
        }else{
        	map.put("msg", "添加失败");
        }
        
        
		return map;
	}
	@Override
	public boolean dAddStudent(Map<String, Object> userMap,Map<String, Object> StuMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		String username = (String)userMap.get("username");
		String password = EncodeMd5.enMD5U32(username);
		userMap.put("userType", "3");
		userMap.put("password", password);
		
		int insert = userInfoDao.dAddUser(userMap);
		UserInfo userInfo = userInfoDao.dGetUser(username);
		int userId = userInfo.getUserId();
		StuMap.put("userId", userId);
		int insertStu = studentDao.dAddStudent(StuMap);
		
		if (insertStu > 0) {
			return true;
		}else{
			return false;
		}
		
	}
	@Override
	public boolean dAddTeacher(Map<String, Object> userMap,Map<String, Object> teachMap) {
		// TODO 自动生成的方法存根
		String username = (String)userMap.get("username");
		String password = EncodeMd5.enMD5U32(username);
		userMap.put("password", password);
		userMap.put("userType", "2");
		int insert = userInfoDao.dAddUser(userMap);
		UserInfo userInfo = userInfoDao.dGetUser(username);
		int userId = userInfo.getUserId();
		teachMap.put("userId", userId);
		int insertTec = teacherDao.dAddTeacher(teachMap);
		if (insertTec > 0) {
			return true;
		}else{
			return false;
		}
		
		
		
	}
	@Override
	public boolean dAddClass(Class class1) {
		int insert = classDao.dAddClass(class1);
		teacherDao.dSetHeadTeacher(class1.getTeacherId());
		if(insert>0){
			return true;
		}else{
			return false;
		}
		
	}
	@Override
	public List<MyDTeacher> dNotTeacherCombobox() {
		// TODO 自动生成的方法存根
		return classDao.dNotTeacherCombobox();
	}
	
}
