package com.tutorialninja.qa.pagesObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
		@FindBy(xpath ="//input[@id='input-firstname']") 
		private WebElement txtfirstName;

		@FindBy(xpath="//input[@id='input-lastname']")  
		private WebElement txtlastName;

		@FindBy(xpath="//input[@id='input-email']")      
		private WebElement txtemail;

		@FindBy(xpath="//input[@id='input-telephone']")  
		private WebElement txttelephone;

		@FindBy(xpath="//input[@id='input-password']")   
		private WebElement txtpwd;

		@FindBy(xpath="//input[@id='input-confirm']")   
		private WebElement txtconfpwd;

		@FindBy(xpath="//input[@name='newsletter'][@value='1']")
		private WebElement selectNewsLetter;
		
		@FindBy(xpath="//input[@name='agree']")         
		private WebElement checkPolicy;

		@FindBy(xpath="//input[@value='Continue']")     
		private WebElement btncontinue;


		  public void setfirstName(String fname)
		  {
			  txtfirstName.sendKeys(fname);
		  }
		  
		  public void setlastName(String lname)
		  {
			  txtlastName.sendKeys(lname);
		  }
		  public void setEmail(String email)
		  {
			  txtemail.sendKeys(email);
		  }
		  public void settelephone(String tphone)
		  {
			  txttelephone.sendKeys(tphone);
		  }
		  public void setpassword(String pwd)
		  {
			  txtpwd.sendKeys(pwd);
		  }
		  public void setconfpassword(String cpwd)
		  {
			  txtconfpwd.sendKeys(cpwd);
			  
		  }
		  public void setNewsletter()
		  {
			  selectNewsLetter.click();
		  }
		  public void setchkPolicy()
		  {
			  checkPolicy.click();
		  }
		  public AccountSuccessPage btcontinue()
		  {
			  btncontinue.click();
			 return new AccountSuccessPage(driver);
		  }
		  
		 
	}

