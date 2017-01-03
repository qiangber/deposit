package com.deposit.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.deposit.entity.Status;
import com.deposit.service.impl.ExcelHandler;

@Controller
@RequestMapping("/deposit")
public class DepositController {
	
	@Autowired
	ExcelHandler excelHandler;
	
	@RequestMapping("/add")
	public ModelAndView uploadFile(@RequestParam("excel") MultipartFile file, HttpServletRequest request) {
		String fileName = file.getOriginalFilename().equals("addUser.xlsx")
				?"addUserTemp":file.getOriginalFilename();
		String filePath = request.getServletContext().getRealPath("resources") + File.separator
				+ "excel" + File.separator + fileName;
		try {
			file.transferTo(new File(filePath));
			Status status = excelHandler.getInfoFromExcel(filePath);
			if (status.getStatus().equals("success")) {
				request.setAttribute("result", status.getMsg());
			} else {
				request.setAttribute("result", "第" + status.getMsg() + "行出错，请检查！");
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("type", "teacher");
		mav.setViewName("userAdd");
		return mav;
	}
}
