package com.tutorialsNinja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorial.qa.base.Base;
import com.tutorialninja.qa.pagesObjects.HomePage;
import com.tutorialninja.qa.pagesObjects.SearchPage;

public class SearchTest extends Base {
	SearchPage searchPage;
	public SearchTest()
	{
		super();
	}
	public WebDriver driver;
	   
	   @BeforeMethod
	   public void setup()
	   {
		    driver = commonCode(prop.getProperty("browser"));
	   }

	   @Test(priority=1)
	    public void verifySearchWithValidProduct()
	    {
		   HomePage homePage = new HomePage(driver);
		   homePage.enterProductName("Hp");
		   searchPage=homePage.clickOnSearchButton();
		   Assert.assertTrue(searchPage.displayStatusOfHpProduct()
				   ,"Valid product HP is not displyed in the search results");
	    }
	   
	   @AfterMethod
	   public void tearDown()
	   {
		   driver.quit();
	   }
	   
	   @Test(priority=2, dependsOnMethods= {"verifySearchWithValidProduct"})
	   public void verifySearchWithInvalidProduct()
	   {HomePage homePage = new HomePage(driver);
	   homePage.enterProductName("Honda");
	   searchPage=homePage.clickOnSearchButton();
		   Assert.assertEquals(searchPage.displayStatusOfInvalidProduct()
				   , "There is no product that matches the search criteria.");
	   }
	   
}  
