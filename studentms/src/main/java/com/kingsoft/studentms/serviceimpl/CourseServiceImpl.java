package com.kingsoft.studentms.serviceimpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kingsoft.studentms.dao.ICourseDao;
import com.kingsoft.studentms.model.Course;
import com.kingsoft.studentms.model.MyAssignment;
import com.kingsoft.studentms.model.MyCourse;
import com.kingsoft.studentms.service.ICourseService;
@Service("courseService")
public class CourseServiceImpl implements ICourseService{

	@Resource
	private ICourseDao courseDao;
	
	@Override
	public List<Course> showCourseAssign(Map<String, Object> map) {
		// TODO 自动生成的方法存根
		return null;
	}
	
	@Override
	public int addCourse(Course course) {
		// TODO 自动生成的方法存根
		return courseDao.addCourse(course);
	}

	@Override
	public int courseAssignSum(Map<String, Object> map) {
		// TODO 自动生成的方法存根
		return courseDao.courseAssignSum(map);
	}

	@Override
	public List<MyAssignment> courseAssignRows(Map<String, Object> map) {
		// TODO 自动生成的方法存根
		return courseDao.selectCourseAssignment(map);
	}

	@Override
	public int courseSum(Map<String, Object> map) {
		// TODO 自动生成的方法存根
		return courseDao.courseSum(map);
	}

	@Override
	public List<MyCourse> courseRows(Map<String, Object> map) {
		// TODO 自动生成的方法存根
		return courseDao.selectCourse(map);
	}

	
	

	

}
