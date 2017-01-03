package com.deposit.service;

import java.util.Date;
import java.util.List;

import com.deposit.entity.Standard;

public interface StandardService {
	
	public int add(Standard standard);
	
	public int deleteNotToday(Date time);
	
	public List<String> getExchangeList();
	
	public List<String> getPropertyByParam(String property, String exchange);
	
	public Double getDeposit(Standard standard, int money, int hand);
	
}
