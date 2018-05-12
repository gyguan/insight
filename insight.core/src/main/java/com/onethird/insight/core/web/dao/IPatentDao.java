package com.onethird.insight.core.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.onethird.insight.core.web.entity.PatentBean;

public interface IPatentDao {
	public List<PatentBean> getPatentInfoByCompanyId(@Param("companyId") Long companyId);
}
