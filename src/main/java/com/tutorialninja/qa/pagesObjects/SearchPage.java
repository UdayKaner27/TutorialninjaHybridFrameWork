package com.tutorialninja.qa.pagesObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	WebDriver driver;
	public SearchPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText="HP LP3065")
	private WebElement validHpProduct;
	
	@FindBy(xpath="//p[contains(text(),'There is no product that matches the search criter')]")
	private WebElement inValidProduct;
	
	public boolean displayStatusOfHpProduct()
	{
	  boolean displayStatus =validHpProduct.isDisplayed();
	return displayStatus;
	}

	public String displayStatusOfInvalidProduct()
	{
	 String displayInvalidStatus= inValidProduct.getText();
	return displayInvalidStatus;
	
	}
}
