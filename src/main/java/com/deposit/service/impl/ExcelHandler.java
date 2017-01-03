package com.deposit.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deposit.entity.Standard;
import com.deposit.entity.Status;
import com.deposit.service.StandardService;

@Service("excelHandler")
public class ExcelHandler {
	
	@Autowired
	private StandardService standardService;
	
	private int i = 0;

	public Status getInfoFromExcel(String filePath) {
		standardService.deleteNotToday(new Date());
		Workbook workbook = null;
		try {
			if (isExcel2003(filePath) && !isExcel2007(filePath)) {
				workbook = new HSSFWorkbook(new FileInputStream(filePath));
			} else {
				workbook = new XSSFWorkbook(new FileInputStream(filePath));
			}
			Sheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			System.out.println(rows);
			rows: for (i = 3; i < rows; i++) {
				Row row = sheet.getRow(i);
				if (row != null) {
					int cells = 7;
					List<String> list = new ArrayList<String>();
					for (int j = 0; j < cells; j++) {
						Cell cell;
						if (isMergedRegion(sheet, i, j)) {
							cell = getMergedRegionValue(sheet, i, j);
						} else {							
							cell = row.getCell(j);
						}
						if (cell != null) {
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_FORMULA:
								break;
							case Cell.CELL_TYPE_NUMERIC:
								if (cell.getCellStyle().getDataFormatString().indexOf("%") != -1) {
									list.add((cell.getNumericCellValue() + "").trim());
								} else {
									list.add(((int)cell.getNumericCellValue() + "").trim());									
								}
								break;
							case Cell.CELL_TYPE_STRING:
								list.add(cell.getStringCellValue().trim());
								break;
							default:
								list.add("");
								break;
							}
						} else {
							list.add("");
						}
					}
					if (list.get(0) == null || list.get(0).equals("")) {
						break rows;
					}
					Standard s = new Standard();
					int k = 0;
					s.setExchange(list.get(k++));
					s.setName(list.get(k++));
					s.setStandard(Double.parseDouble(list.get(k++)));
					String unit = list.get(list.size()-1).replaceAll("\\D", "");
					s.setUnit(Integer.parseInt(unit));
					s.setTime(new Date());
					standardService.add(s);
				}
			}
			return new Status("success", "添加成功");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new Status("error", i + "");
		} catch (IOException e) {
			e.printStackTrace();
			return new Status("error", i + "");
		} catch (Exception e) {
			e.printStackTrace();
			return new Status("error", i + "");
		}
	}

	private boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");
	}

	private boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}
	
	private boolean isMergedRegion(Sheet sheet, int row, int column) {
		int mergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < mergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();  
			int lastColumn = range.getLastColumn();  
			int firstRow = range.getFirstRow();  
			int lastRow = range.getLastRow(); 
			if (row <= lastRow && row >= firstRow && column <= lastColumn && column >= firstColumn) {
				return true;
			}
		}
		return false;
	}
	
	private Cell getMergedRegionValue(Sheet sheet ,int row , int column){    
	    int sheetMergeCount = sheet.getNumMergedRegions();    
	        
	    for(int i = 0 ; i < sheetMergeCount ; i++){    
	        CellRangeAddress ca = sheet.getMergedRegion(i);    
	        int firstColumn = ca.getFirstColumn();    
	        int lastColumn = ca.getLastColumn();    
	        int firstRow = ca.getFirstRow();    
	        int lastRow = ca.getLastRow();    
	            
	        if(row >= firstRow && row <= lastRow && column >= firstColumn && column <= lastColumn){	                
                Row fRow = sheet.getRow(firstRow);    
                Cell fCell = fRow.getCell(firstColumn);    
                return fCell;    
	        }    
	    }	        
	    return null ;    
	}
}
