package com.Tutorialsninja.qa.listners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialninja.qa.utils.ExtentReporter;
import com.tutorialninja.qa.utils.Utilities;


public class MyListners implements ITestListener {
  	
  ExtentReports extentReport;
  ExtentTest extentTest;
  String testName ; //making it global
	@Override
	public void onStart(ITestContext context) {
	 extentReport = ExtentReporter.generateExtentReport();
	}
	@Override
	public void onTestStart(ITestResult result) {
		 testName = result.getName();
		extentTest =extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName+"Started executing");  //--1--
		//2//extentTest.log(Status.INFO,result.getName+"Started executing");
		//System.out.println(testName+"started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	//	 testName = result.getName();
		extentTest.log(Status.PASS, testName+"got successfully executed");		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
    //  	testName =result.getName(); 
		//String testName =result.getName(); 
		testName =result.getName(); 
	    WebDriver driver =null;
	    
	    try {
			driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	    //below method is coming from Utilities package
       String targetFile =  Utilities.captureScreenshot(driver, result.getName());
        extentTest.addScreenCaptureFromPath(targetFile);
    	extentTest.log(Status.INFO,result.getThrowable());
   	    extentTest.log(Status.FAIL,result.getName()+"got failed");
        

	}

	@Override
	public void onTestSkipped(ITestResult result) {
	 //   testName=result.getName();
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP,testName+"got skipped");
	
	}

	@Override
	public void onFinish(ITestContext context) {
		
    extentReport.flush();
    String pathOfExtentReport = System.getProperty("user.dir")
    		+"\\test-output\\ExtentReports\\extentReport.html";
    File extnetReport = new File(pathOfExtentReport);
    try {
		Desktop.getDesktop().browse(extnetReport.toURI());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	 
	
}
