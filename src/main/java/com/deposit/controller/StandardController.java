package com.deposit.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.deposit.entity.Standard;
import com.deposit.entity.Status;
import com.deposit.service.StandardService;
import com.deposit.service.impl.ExcelHandler;

@Controller
@RequestMapping("/standard")
public class StandardController {
	
	@Autowired
	ExcelHandler excelHandler;
	
	@Autowired
	StandardService standardService;
	
	@RequestMapping("/preAdd")
	public String preAdd() {
		return "add";
	}
	
	@RequestMapping("/add")
	public ModelAndView uploadFile(@RequestParam("excel") MultipartFile file, HttpServletRequest request) {
		String fileName = file.getOriginalFilename();
		String filePath = request.getServletContext().getRealPath("excel") + File.separator + fileName;
		try {
			file.transferTo(new File(filePath));
			Status status = excelHandler.getInfoFromExcel(filePath);
			if (status.getStatus().equals("success")) {
				request.setAttribute("result", status.getMsg());
			} else {
				request.setAttribute("result", "第" + status.getMsg() + "行出现错误！");
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("add");
		return mav;
	}
	
	@RequestMapping("/api/getProperty")
	public @ResponseBody Map<String, List<String>> getProperty(
			@RequestParam(value="property", required=true) String property,
			@RequestParam(value="exchange", required=true) String exchange) {
		List<String> list = standardService.getPropertyByParam(property, exchange);
		Map<String, List<String>> map = new HashMap<String, List<String>>(2);
		map.put("result", list);
		return map;
	}
	
	@RequestMapping("/api/calculate")
	public @ResponseBody Map<String, String> calculate(
			Standard standard,
			@RequestParam(value="money", required=true) Integer money,
			@RequestParam(value="hand", required=true) Integer hand) {
		Map<String, String> map = new HashMap<String, String>(2);		
		map.put("result", String.format("%.2f", standardService.getDeposit(standard, money, hand)));
		return map;		
	}
}
