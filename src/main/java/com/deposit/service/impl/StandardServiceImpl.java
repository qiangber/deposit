package com.deposit.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.deposit.dao.StandardDao;
import com.deposit.entity.Standard;
import com.deposit.service.StandardService;

@Service("standardService")
public class StandardServiceImpl implements StandardService {
	
	@Resource
	private StandardDao standardDao;
	
	public int add(Standard standard) {
		return standardDao.add(standard);
	}
	
	public int deleteNotToday(Date time) {
		return standardDao.deleteNotToday(time);
	}
	
	public List<String> getExchangeList() {
		return standardDao.getExchangeList();
	}
	
	public List<String> getPropertyByParam(String property, String exchange) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("property", property);
		map.put("exchange", exchange);
		return standardDao.getPropertyByParam(map);
	}
	
	public Double getDeposit(Standard standard, int money, int hand) {
		standard = standardDao.get(standard);
		return standard.getStandard() * standard.getUnit() * money * hand;
	}
	
}
