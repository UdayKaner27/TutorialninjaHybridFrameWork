package com.tutorialsNinja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorial.qa.base.Base;
import com.tutorialninja.qa.pagesObjects.AccountPage;
import com.tutorialninja.qa.pagesObjects.HomePage;
import com.tutorialninja.qa.pagesObjects.LoginPage;
import com.tutorialninja.qa.utils.Utilities;

public class LoginTest extends Base{
 
   LoginPage loginPage;
   AccountPage accountPage;
   
   public LoginTest()
   {
	   super();
   }
   public WebDriver driver;
   @BeforeMethod
   public void setup()
   {
	   driver= commonCode(prop.getProperty("browser"));
	   HomePage HomePage = new HomePage(driver);
	   HomePage.clickOnMyAccount();
	   loginPage=HomePage.selectLoginOption();
	  
   }
   
	@Test(priority =1,dataProvider="supplyTestData")
	public void verifyLoginWithValidCredentials(String email,String password)
	{
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		 accountPage=loginPage.clickOnLoginButton();
		//accountPage.getDisplayStatusOfEditYourAccountInformation();
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption());
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials()
	{
		loginPage.enterEmailAddress(Utilities.randomEmail()+Utilities.generateTimeStamp());
		loginPage.enterPassword(Utilities.randomPwd());
		loginPage.clickOnLoginButton();
		
		String actualWarning = loginPage.retrieveWarningMessage();
		String expetedWarning =driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		Assert.assertTrue(actualWarning.contains(expetedWarning), "Expected Warning message is not displayed");
    
	}
	
	@AfterMethod
	public void tearDown()
	{
	driver.quit();
	}
	
	@DataProvider
	 public Object[][] supplyTestData()
	 {
		Object[][] data= Utilities.getTestDataFromExcel("Sheet1");
		return data;
		
	 }
}
