package com.awinbv.rockzent.generalUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

/**
 * It is developed using Apache POI libraries which is used to handle MS excel sheet
 * @author Chinmaya
 *
 */
public class ExcelUtility {
	/**
	 * It is used to read the data from the excel based on below arguments
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return
	 * @throws Throwable
	 */
	public static String getDataFromExcel(String sheetName, int rowNum, int cellNum) throws Throwable {
		FileInputStream fis = new FileInputStream("./src/test/resources/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		String data = row.getCell(cellNum).getStringCellValue();
		wb.close();
		return data;
	}
	
	/**
	 * It is used to get the last used row number on specified sheet
	 * @param sheetName
	 * @param rowNum
	 * @return
	 * @throws Throwable
	 */
	public int getRowCount(String sheetName) throws Throwable {
		FileInputStream fis = new FileInputStream("./data/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		wb.close();
		return sh.getLastRowNum();
	}
	
	/**
	 * It is used to set/write the data to the excel file
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @param data
	 * @throws Throwable
	 */
	public void setDataToExcel(String sheetName, int rowNum, int cellNum, String data) throws Throwable {
		FileInputStream fis = new FileInputStream("./data/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cell = row.createCell(cellNum);
		cell.setCellValue(data);
		FileOutputStream fos = new FileOutputStream("./data/testScriptData.xlsx");
		wb.write(fos);
		wb.close();
	}
	
	@DataProvider
	public Object[][] getMultipleDataFromExcel(String sheetName, int startRow, int startCol) throws Throwable {
		FileInputStream fis = new FileInputStream("./src/test/resources/testScriptData2.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(startRow);
		int ci,cj;
		int totalRows = sh.getLastRowNum();
		int totalCols = sh.getRow(0).getLastCellNum();
		Object[][] tabArray = new String[totalRows][totalCols];
		ci=0;
		for (int i=startRow;i<=totalRows;i++, ci++) {           	   
			cj=0;
			for (int j=startCol;j<=totalCols;j++, cj++){

				tabArray[ci][cj]=getDataFromExcel(sheetName, i, j);

					}
				}
		return tabArray;
	}

}
