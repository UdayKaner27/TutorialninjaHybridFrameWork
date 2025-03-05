package com.tutorialninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public static ExtentReports generateExtentReport() 
	{
		ExtentReports extentReport = new ExtentReports();
		File extentReportFile = new File(System.getProperty
				("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
	
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Opencart Automation Results");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		Properties configProp = new Properties();
		File configPropFile = new File (System.getProperty
		("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\config\\config.properties");
		
		try {
		FileInputStream fisConfigProp = new FileInputStream(configPropFile);
		configProp.load(fisConfigProp);
		}catch(Throwable e)
		{
			e.printStackTrace();
		}		
		
		extentReport.setSystemInfo("Application URL",configProp.getProperty("url")  );
		extentReport.setSystemInfo("Browser Name",configProp.getProperty("chrome"));
		
		extentReport.setSystemInfo("Email", configProp.getProperty("email"));
		extentReport.setSystemInfo("Password",configProp.getProperty("password"));
		extentReport.setSystemInfo("Application", "opencart");
	    extentReport.setSystemInfo("Module", "Admin");
	    extentReport.setSystemInfo("Sub Module", "Customers");
	    extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
	    extentReport.setSystemInfo("Environment", "QA");
	
	   return extentReport; 
	}
	
}
