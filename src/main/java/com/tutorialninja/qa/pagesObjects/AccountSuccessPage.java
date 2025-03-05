package com.tutorialninja.qa.pagesObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {
	WebDriver driver;
	public AccountSuccessPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	 public void messageConfirmation()
	  {
		  msgConfirmation.click();
	  }
	  
	  public String getConfirmationMsg()
	  {
		  try {
			  return (msgConfirmation.getText());
		  }catch (Exception e) {
		return (e.getMessage());
		  
	  }
}
}