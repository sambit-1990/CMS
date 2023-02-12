package com.CMS.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	String url = "http://www.ginsmartserv.in/CMS/";
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void goToLoginPage()
	{
		driver.get(url);
	}
	
	

}
