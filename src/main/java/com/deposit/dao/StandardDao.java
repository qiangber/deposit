package com.deposit.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deposit.entity.Standard;

public interface StandardDao {
	
	public int add(Standard standard);
	
	public int deleteNotToday(Date time);
	
	public List<String> getExchangeList();
	
	public List<String> getPropertyByParam(Map<String, String> map);
	
	public Standard get(Standard standard);
}
