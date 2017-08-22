package com.kingsoft.studentms.dao;

import com.kingsoft.studentms.model.TeachingPlan;

public interface ITeachingPlanDao extends IBasicDao{
	public int addTeachingPlan(TeachingPlan teachingPlan);
}
