package com.onethird.insight.core.web.controller;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onethird.insight.core.common.entity.ResultBean;
import com.onethird.insight.core.common.handler.NormalResponseHandler;
import com.onethird.insight.core.web.service.IPatentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 公司申请专利相关服务
 * @author GGY
 *
 */

@RestController
@RequestMapping("/api/patent/company")
public class CompanyPatentController extends NormalResponseHandler{
	@Autowired
	private IPatentService patentService;
	
	@ApiOperation("通过厂商id获取公司每年申请专利的时间曲线数据")
	@GetMapping("/{companyId}/timeline")
	public ResultBean<Map<String, Object>> getCompanyPatentTimeCurve(
			@ApiParam(name = "companyId", required = true)
			@PathVariable("companyId") Long companyId) throws ParseException{
		Map<String, Object> timeCurveData = patentService.getCompanyPatentTimeCurve(companyId);
		return response(timeCurveData);
	}
	
	@ApiOperation("通过厂商id获取厂商TOP N专利申请人员")
	@GetMapping("/{companyId}/topInventor")
	public ResultBean<Map<String, Object>> getCompanyTopInventor(
			@ApiParam(name = "companyId", required = true)
			@PathVariable("companyId") Long companyId) throws ParseException{
		Map<String, Object> inventorData = patentService.getCompanyTopInventor(companyId);
		return response(inventorData);
	}
	
	@ApiOperation("通过厂商id获取厂商申请专利最多的IPC分类")
	@GetMapping("/{companyId}/topIpc")
	public ResultBean<Map<String, Object>> getCompanyTopIpc(
			@ApiParam(name = "companyId", required = true)
			@PathVariable("companyId") Long companyId) throws ParseException{
		Map<String, Object> inventorData = patentService.getCompanyTopIpc(companyId);
		return response(inventorData);
	}
	
}
