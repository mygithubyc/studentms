package com.kingsoft.studentms.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.kingsoft.studentms.dao.ICommitInfoDao;
import com.kingsoft.studentms.service.ICommitInfoService;

@Service("commitInfoService")
public class CommitInfoServiceImpl implements ICommitInfoService {

	@Resource
	private ICommitInfoDao commitInfoDao;

	/**
	 * @通过发布id获得本次作业的提交记录
	 */
	@Override
	public String getCommitByPid(String publishId) {
		// TODO Auto-generated method stub
		List<?> commitList = commitInfoDao.getCommitList(Integer.parseInt(publishId));
		return JSON.toJSONString(commitList);
	}
}
