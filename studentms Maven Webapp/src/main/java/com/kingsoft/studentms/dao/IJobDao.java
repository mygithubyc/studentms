package com.kingsoft.studentms.dao;

import com.kingsoft.studentms.model.Job;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface IJobDao {
    int deleteByPrimaryKey(BigDecimal jid);

    int insert(Job record);

    int insertSelective(Job record);

    Job selectByPrimaryKey(BigDecimal jid);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);

    Integer selectJobCount(String username);
}