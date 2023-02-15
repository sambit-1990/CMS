package com.CMS.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	String url = "http://www.ginsmartserv.in/CMS/";
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="Login1_UserName")
	WebElement UserName;
	
	@FindBy(id="Login1_Password")
	WebElement Password;
	
	@FindBy(xpath="(//input[@type='submit'])[1]")
	WebElement submitButton;
	
	
	public void goToLoginPage()
	{
		driver.get(url);
	}
	
	public void enterUsername(String username)
	{
		UserName.sendKeys(username);
	}
	
	public void enterPassword(String password)
	{
		Password.sendKeys(password);
	}
	
	public void clickSubmitButton()
	{
		submitButton.click();
	}
	

}
