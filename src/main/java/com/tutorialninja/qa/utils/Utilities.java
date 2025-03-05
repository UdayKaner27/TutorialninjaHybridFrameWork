package com.tutorialninja.qa.utils;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static String randomEmail()
	{
		String generateRandom = RandomStringUtils.randomAlphabetic(7);
		return generateRandom;
	}
	public static String randomPwd()
	{
		String generateRandomPwd = RandomStringUtils.randomAlphanumeric(7);
		return generateRandomPwd;
	}
	public static String generateTimeStamp()
	{
		Date date = new Date();
		return date.toString().replace(" ", " _").replace(":", "_");
	}
   public static String generateTelephone()
   {
	   String generateRandomTelephone = RandomStringUtils.random(10);
	return generateRandomTelephone;
   }
   public static String generateFnameAndLname()
   {
	   String generateRandomFLname = RandomStringUtils.randomAlphabetic(5);
	return generateRandomFLname;  
   }
   public static final int IMPLICIT_WAIT_TIME = 10;
   
   public static Object[][] getTestDataFromExcel(String sheetName) 
   {
	  File excelFile = new File((System.getProperty
		("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\testdata\\TutorialninjaTestData.xlsx"));
	  XSSFWorkbook workbook = null; 
	 
	  try {
	  FileInputStream fis = new FileInputStream(excelFile);
	    workbook = new XSSFWorkbook(fis);
	   }catch(Throwable e)
	   {
		   e.printStackTrace();
	   }
	   
	   XSSFSheet sheet = workbook.getSheet(sheetName);
	 
	   int  rows = sheet.getLastRowNum();
	   int cells = sheet.getRow(0).getLastCellNum();
	   
	   Object[][] data = new Object [rows][cells];
	   
	   for(int i=0;i<rows;i++)
	   {
		   XSSFRow currentRow = sheet.getRow(i+1);
	   
	   for(int j=0;j<cells;j++)
	   {
		   XSSFCell currentCell = currentRow.getCell(j);
		   CellType cellType = currentCell.getCellType();
		   switch(cellType)
		   {
		   case STRING:
			   data[i][j] = currentCell.getStringCellValue();
			   break;
		   case NUMERIC:
			   data[i][j] =Integer.toString((int)currentCell.getNumericCellValue());
			   break;
		   case BOOLEAN:
			   data[i][j] = currentCell.getBooleanCellValue();
			   break;
		   }
	   }
	  }
	return data;
   }
   public static String captureScreenshot(WebDriver driver, String testName)
   {
	   File sourceFile = 	((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
       String targetFile = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
       
       try {
			FileHandler.copy(sourceFile, new File(targetFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
       return targetFile;
   }
}
