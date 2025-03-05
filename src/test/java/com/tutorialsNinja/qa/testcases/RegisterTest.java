package com.tutorialsNinja.qa.testcases;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorial.qa.base.Base;
import com.tutorialninja.qa.pagesObjects.AccountSuccessPage;
import com.tutorialninja.qa.pagesObjects.HomePage;
import com.tutorialninja.qa.pagesObjects.RegisterPage;
import com.tutorialninja.qa.utils.Utilities;

public class RegisterTest extends Base {
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	public RegisterTest()
	{
		super();
	}
	 public  WebDriver driver;
	   @BeforeMethod
	   public void setup()
	   {
		   driver = commonCode(prop.getProperty("browser"));
		   HomePage HomePage = new HomePage(driver);
		   HomePage.clickOnMyAccount();
		   registerPage=HomePage.selectRegisterOption();
	   }
	   
	   @Test(priority=1)
	public void verifyRegisterningAnAccountWithMandatoryFields()
  {
		registerPage.setfirstName(Utilities.generateFnameAndLname());
	    registerPage.setlastName(Utilities.generateFnameAndLname());
		registerPage.setEmail(Utilities.randomEmail()+"@gmail.com");
		registerPage.settelephone(dataProp.getProperty("intTelephone"));
		String passwordtxt = Utilities.randomPwd();
		registerPage.setpassword(passwordtxt);
		registerPage.setconfpassword(passwordtxt);
		registerPage.setchkPolicy();
		accountSuccessPage=registerPage.btcontinue();
			
      
			

		  String confmsg =accountSuccessPage.getConfirmationMsg();
		  Assert.assertEquals(confmsg, "Your Account Has Been Created!");
	  
		  driver.quit();
         }
	   
	   
	   @Test(priority=2)
	   public void verifyRegisteringAnAccountByProvidingAllFileds()
	   {
		registerPage.setfirstName(Utilities.generateFnameAndLname());
	    registerPage.setlastName(Utilities.generateFnameAndLname());
	    registerPage.setEmail(Utilities.randomEmail()+"@gmail.com");
		registerPage.settelephone(dataProp.getProperty("intTelephone"));
		String passwordtxt = Utilities.randomPwd();
		registerPage.setpassword(passwordtxt);
		registerPage.setconfpassword(passwordtxt);
		registerPage.setNewsletter();
		registerPage.setchkPolicy();
		accountSuccessPage=registerPage.btcontinue();       
		
		String confmsg =driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
	   
		  driver.quit();
	   }
	   @Test(priority=3)
	   public void verifyRegisteringAccountWithExistingEmailAddress()
	   {
	    driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("uday");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("kaner");
			
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(prop.getProperty("email"));
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys(dataProp.getProperty("intTelephone")); 
			
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys(prop.getProperty("password"));
		
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		try {
		String confmsg =driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		  Assert.assertEquals(confmsg, "Warning: E-Mail Address is already registered!");
	       
		  driver.quit();
		}catch(Exception e)
		{
			Assert.fail();
		}
			
	}
	   
  }

