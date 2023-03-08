package com.CMS.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class UIControlAction {

	WebDriver driver;
	

	public UIControlAction(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void moveToWebElement(WebElement element) {
		Actions Action = new Actions(driver);
		Action.moveToElement(element).build().perform();
	}

}
