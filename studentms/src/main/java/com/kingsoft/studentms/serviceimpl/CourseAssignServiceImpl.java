package com.kingsoft.studentms.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingsoft.studentms.dao.ICourseAssignDao;
import com.kingsoft.studentms.service.ICourseAssignService;

@Service("courseAssignService")
public class CourseAssignServiceImpl implements ICourseAssignService{
	@Resource
	private ICourseAssignDao courseAssignDao;
	@Override
	public boolean addCourseAssigns(Map<String, Object> map) {
		String term = (String) map.get("semester");
		String departId = (String) map.get("departId");
		System.out.println("addCourseAssigns 部门id"+departId);
		JSONArray array=  JSONArray.parseArray((String) map.get("ids"));
		if (array.size()>0) {
			 for(int i=0;i<array.size();i++){
				 Map<String, Object> addMap = new HashMap<String, Object>();
				 JSONObject obj = array.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
				 addMap.put("courseId", obj.get("id"));
				 addMap.put("term", term);
				 addMap.put("departId", departId);
				 int insert = courseAssignDao.addCourseAssign(addMap);
				 if (insert < 1) {
					return false;
				}
			  }
		}
		
		return true;
	}
	@Override
	public int cancleAssignByDepart(String id) {
		// TODO 自动生成的方法存根
		return courseAssignDao.deleteCourseByDepary(id);
	}
	
}
