package com.tutorialninja.qa.pagesObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;


public class LoginPage  {
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
    @FindBy(xpath="//input[@id='input-email']")
   private WebElement emailAddressField;
    
    @FindBy(xpath="//input[@id='input-password']")
   private WebElement passwordField;
    
    @FindBy(xpath="//input[@value='Login']")
   private WebElement loginButton;
    
    @FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
   private WebElement emailPasswordNotMatchingWarning;

    
    public void enterEmailAddress(String emailText)
    {
    	emailAddressField.sendKeys(emailText);
    }
    public  void enterPassword(String passwordText)
    {
    	passwordField.sendKeys(passwordText);
    }
    public AccountPage clickOnLoginButton()
    {
    	loginButton.click();
        return new AccountPage(driver);
    }
    public String retrieveWarningMessage()
    {
    	String warningText =emailPasswordNotMatchingWarning.getText();
		return warningText;
    }
}
    
