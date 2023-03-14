package com.CMS.Utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.CMS.TestCases.BaseTest;

public class WaitUtilities extends BaseTest{
	
	WebDriver driver;
	
	public WaitUtilities(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	
	public void WaitExplicitly(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	

}
