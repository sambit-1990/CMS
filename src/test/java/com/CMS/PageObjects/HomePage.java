package com.CMS.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id ="ctl00_LoginStatus1")
	public WebElement logout;
	
	public String getPageTitle()
	{
		String pageTitle = driver.getTitle();
		return pageTitle;
	}
	
	public void clickLogOut()
	{
		logout.click();
	}

}
