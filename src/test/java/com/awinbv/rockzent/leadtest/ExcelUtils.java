package com.awinbv.rockzent.leadtest;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.awinbv.rockzent.generalUtility.ExcelUtility;

public class ExcelUtils {
	
	@Test(dataProvider = "getMultipleDataFromExcel")
	public void excelData(String first, String second) throws Throwable {
		ExcelUtility eLib = new ExcelUtility();
		System.out.println(first + second);
	}
	
	@DataProvider
	public Object[][] getMultipleDataFromExcel() throws Throwable {
		FileInputStream fis = new FileInputStream("./src/test/resources/testScriptData2.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("LeadsModule");
		Row row = sh.getRow(1);
		int ci,cj;
		int totalRows = sh.getLastRowNum();
		int totalCols = 2;
		Object[][] tabArray = new Object[totalRows][totalCols];
		ci=0;
		for (int i=1;i<totalRows;i++, ci++) {           	   
			cj=0;
			for (int j=0;j<totalCols;j++, cj++){

				tabArray[ci][cj]=ExcelUtility.getDataFromExcel("LeadsModule", i, j);

					}
				}
		return tabArray;
	}

}
