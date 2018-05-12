package com.onethird.insight.core.web.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onethird.insight.core.web.dao.IPatentDao;
import com.onethird.insight.core.web.entity.PatentBean;
import com.onethird.insight.core.web.service.IPatentService;

@Service("patentService")
public class PatentService implements IPatentService{

	@Autowired
	private IPatentDao patentDao;

	@Override
	public Map<String, Object> getCompanyPatentTimeCurve(Long companyId) throws ParseException {
		List<PatentBean> patentBeanList = patentDao.getPatentInfoByCompanyId(companyId);
		Map<String, Integer> dataMap = new HashMap<String, Integer>();
//		DateFormat format = new SimpleDateFormat("yyyy");
		String companyName = (String) patentBeanList.get(0).getExtendData().get("companyName");
		for(PatentBean patentBean : patentBeanList) {
			String dateString = patentBean.getApplicationDate().substring(0, 4);
//			long dateMillis = getTimeMillis(format, patentBean.getApplicationDate());
			if(dataMap.containsKey(dateString)) {
				dataMap.put(dateString, dataMap.get(dateString) + 1);
			}else {
				dataMap.put(dateString, 1);
			}
		}
		Map<String, Integer> sortMap = new TreeMap<String, Integer>(new Comparator<String>(){
			@Override
			public int compare(String str1, String str2) {
				return str1.compareTo(str2);
			}
		});
		sortMap.putAll(dataMap);
		List<Map<String, String>> dataMapList = new ArrayList<Map<String, String>>();
		for(Entry<String, Integer> entry : sortMap.entrySet()) {
			Map<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("year", entry.getKey());
			tempMap.put("count", String.valueOf(entry.getValue()));
			dataMapList.add(tempMap);
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("companyName", companyName);
		resultMap.put("data", dataMapList);
		return resultMap;
	}


	public static long getTimeMillis(DateFormat format, String dateString) throws ParseException {
		Date date = null;
		dateString = dateString != null ? dateString.trim() : "";
		if(dateString.length() > 0) {
			date = format.parse(dateString);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.getTimeInMillis();
		}else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Map<String, Object> getCompanyTopInventor(Long companyId) throws ParseException {
		List<PatentBean> patentBeanList = patentDao.getPatentInfoByCompanyId(companyId);
		String companyName = (String) patentBeanList.get(0).getExtendData().get("companyName");
		Map<String, String> dataMap = new HashMap<String, String>();
		for(PatentBean patentBean : patentBeanList) {
			if(null == patentBean.getInventor())
				continue;
			String[] inventorArray = patentBean.getInventor().split(";");
			for(String inventor : inventorArray) {
				if(dataMap.containsKey(inventor)) {
					dataMap.put(inventor, String.valueOf(Integer.valueOf(dataMap.get(inventor)) + 1));
				}else {
					dataMap.put(inventor, String.valueOf(1));
				}
			}
		}
		Map<String, String> sortMap = sortMapByValue(dataMap);
		List<Map<String, String>> dataMapList = new ArrayList<Map<String, String>>();
		int count = 1;
		for(Entry<String, String> entry : sortMap.entrySet()) {
			if(count > 10)
				break;
			Map<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("inventor", entry.getKey());
			tempMap.put("count", entry.getValue());
			dataMapList.add(tempMap);
			count ++;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("companyName", companyName);
		resultMap.put("data", dataMapList);
		return resultMap;
	}

	@Override
	public Map<String, Object> getCompanyTopIpc(Long companyId) throws ParseException {
		List<PatentBean> patentBeanList = patentDao.getPatentInfoByCompanyId(companyId);
		String companyName = (String) patentBeanList.get(0).getExtendData().get("companyName");
		Map<String, String> dataMap = new HashMap<String, String>();
		for(PatentBean patentBean : patentBeanList) {
			if(null == patentBean.getIpc())
				continue;
			String[] ipcArray = patentBean.getIpc().split(";");
			for(String ipc : ipcArray) {
				if(dataMap.containsKey(ipc)) {
					dataMap.put(ipc, String.valueOf(Integer.valueOf(dataMap.get(ipc)) + 1));
				}else {
					dataMap.put(ipc, String.valueOf(1));
				}
			}
		}
		Map<String, String> sortMap = sortMapByValue(dataMap);
		List<Map<String, String>> dataMapList = new ArrayList<Map<String, String>>();
		int count = 1;
		for(Entry<String, String> entry : sortMap.entrySet()) {
			if(count > 10)
				break;
			Map<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("ipc", entry.getKey());
			tempMap.put("count", entry.getValue());
			dataMapList.add(tempMap);
			count ++;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("companyName", companyName);
		resultMap.put("data", dataMapList);
		return resultMap;
	}
	
	public static Map<String, String> sortMapByValue(Map<String, String> oriMap) {
		if (oriMap == null || oriMap.isEmpty()) {
			return null;
		}
		Map<String, String> sortedMap = new LinkedHashMap<String, String>();
		List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(
				oriMap.entrySet());
		Collections.sort(entryList, new Comparator<Map.Entry<String, String>>() {

			@Override
			public int compare(Entry<String, String> me1, Entry<String, String> me2) {

				return me2.getValue().compareTo(me1.getValue());
			}
		});

		Iterator<Map.Entry<String, String>> iter = entryList.iterator();
		Map.Entry<String, String> tmpEntry = null;
		while (iter.hasNext()) {
			tmpEntry = iter.next();
			sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
		}
		return sortedMap;
	}

}
