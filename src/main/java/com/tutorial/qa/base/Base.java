package com.tutorial.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialninja.qa.utils.Utilities;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	public Base()  //constructor
	{
		 prop = new Properties();
	    File propFile = new File(System.getProperty
	    		("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\config\\config.properties");
	  
	    dataProp= new Properties();
	    File dataPropFile = new File(System.getProperty
	    		("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\testdata\\testdata.properties");
	   try {
	    FileInputStream fisDP = new FileInputStream(dataPropFile);
	    dataProp.load(fisDP);
	   }catch(Throwable e1)
	   {
		   e1.printStackTrace();
	   }
	   
	   
	    try {
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);
	   }catch(Throwable e)
	   {
		   e.printStackTrace();
	   }
	}
	
	public WebDriver commonCode(String browserName) 
	{
		
		switch(browserName.toLowerCase()) {
		case"chrome" : driver = new ChromeDriver(); break;
		case"edge" : driver = new EdgeDriver();break;
		case"firefox" : driver = new FirefoxDriver(); break;
		default : 
			System.out.println("wrong browser");
			return driver; //
	}
//		 String browserName1="chrome";
//		   
//		   if(browserName1.equalsIgnoreCase("chrome"))
//		   {
//			   	driver= new ChromeDriver();
//
//		   }else if(browserName1.equalsIgnoreCase("edge")) {
//			   driver = new EdgeDriver();
//		   }
	
		    driver.manage().deleteAllCookies();
			driver.get(prop.getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
			return driver;
	}

}
