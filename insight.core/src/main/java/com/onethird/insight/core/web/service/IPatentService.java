package com.onethird.insight.core.web.service;

import java.text.ParseException;
import java.util.Map;

public interface IPatentService {
	public Map<String, Object> getCompanyPatentTimeCurve(Long companyId) throws ParseException;
	
	public Map<String, Object> getCompanyTopInventor(Long companyId) throws ParseException; 
	
	public Map<String, Object> getCompanyTopIpc(Long companyId) throws ParseException; 
}
