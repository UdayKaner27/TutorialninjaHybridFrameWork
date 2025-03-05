package com.tutorialninja.qa.pagesObjects;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Login")
	private	WebElement loginOption;
	 
	@FindBy(linkText="Register")
	private  WebElement registerOption;
	
	@FindBy(xpath="//input[@placeholder='Search']")
	private WebElement searchBoxFiled;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	private WebElement searchButton;
	
	public void clickOnMyAccount()
	{
		myAccountDropMenu.click();
	}
	
	public LoginPage selectLoginOption()
	{
	loginOption.click();
	return new LoginPage(driver);
	}
	
   public RegisterPage selectRegisterOption()
   {
	   registerOption.click();
	   return new RegisterPage(driver);
   }
   
   public void enterProductName(String productText)
   {
   searchBoxFiled.sendKeys(productText);
   }
   
   public SearchPage clickOnSearchButton()
   {
	   searchButton.click();
	   return new SearchPage(driver);
   }
}
