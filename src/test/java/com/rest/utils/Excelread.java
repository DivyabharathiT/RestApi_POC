package com.rest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excelread {
	private FileInputStream fis;
	private Workbook wb;
	private Sheet sh;
	private Cell cell;
	private String excelFilePath;
	private Map<String, Integer> columns = new HashMap<>();
	public  void setExcelFile(String SheetName) throws Exception {
		try {
			String path = System.getProperty("user.dir") + "\\Excel\\API_testdata.xlsx";
			File f = new File(path);
			if (!f.exists()) {
				f.createNewFile();
				System.out.println("File doesn't exist, so created!");
			}
			fis = new FileInputStream(path);
			wb = WorkbookFactory.create(fis);
			sh = wb.getSheet(SheetName);
			if (sh == null) {
				sh = wb.createSheet(SheetName);
			}
			this.excelFilePath = path;
			//adding all the column header names to the map 'columns'
			sh.getRow(0).forEach(cell ->{
				columns.put(cell.getStringCellValue(), cell.getColumnIndex());
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public  String getCellData(int rownum, int colnum) throws Exception{
		try{
			cell = sh.getRow(rownum).getCell(colnum);
			String CellData = null;
			switch (cell.getCellType()){
			case STRING:
				CellData = cell.getStringCellValue();
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell))
				{
					CellData = String.valueOf(cell.getDateCellValue());
				}
				else
				{
					CellData = String.valueOf((long)cell.getNumericCellValue());
				}
				break;
			case BOOLEAN:
				CellData = Boolean.toString(cell.getBooleanCellValue());
				break;
			case BLANK:
				CellData = "";
				break;
			}
			return CellData;
		}catch (Exception e){
			return"";
		}
	}



	public  String getCellData(String sheetname,String columnName, int rownum) throws Exception {
		setExcelFile(sheetname);    
		String value = getCellData(rownum, columns.get(columnName));
		return value;
	}

}
