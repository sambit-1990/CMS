package com.CMS.PageObjects;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.CMS.TestCases.BaseTest;
import com.CMS.Utilities.UIControlAction;

public class HomePage extends BaseTest {

	WebDriver driver;
	
	

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "ctl00_LoginStatus1")
	public WebElement logout;

	@FindBy(xpath = "//a[contains(text(),'Free Stock Report')]")
	WebElement freeStockReportLink;

	@FindBy(xpath = "//body//h1")
	WebElement textMessage;
	
	@FindBy(xpath = "//div[@id='ctl00_ContentPlaceHolder1_ASPxPopupControlSalesAndService_ASPxButtonSpares4_CD'] ")
	WebElement serviceOption;
	
	@FindBy(xpath = "//span[@title='Menu']")
	public WebElement menuOption;
	
	@FindBy(xpath = "//a[@href='/CMS/Modules/CCS/Complaint/Default.aspx']")
	WebElement secondOptionFromDropdownMenu;
	
	@FindBy(id = "ctl00_HyperLinkModules0")
	public WebElement moduleLink;
	
	@FindBy(id = "ctl00_ContentPlaceHolder1_ASPxPopupControlSalesAndService_ASPxButtonPumps2Img")
	WebElement salesOption;
	
	@FindBy(xpath = "//a[@href='/CMS/Modules/Pumps/Sales/SalesDefault.aspx']")
	public WebElement secondOptionFromDropdownSalesMenu;

	public String getPageTitle() {
		String pageTitle = driver.getTitle();
		return pageTitle;
	}

	public void clickLogOut() {
		logout.click();
	}

	public void checkGinFreeStock() throws InterruptedException {
		freeStockReportLink.click();
		Set<String> st = driver.getWindowHandles();
		Iterator<String> itr = st.iterator();
		String parent_window = driver.getWindowHandle();
		while (itr.hasNext()) {
			String child_window = itr.next();

			if (!parent_window.equalsIgnoreCase(child_window)) {
				driver.switchTo().window(child_window);
				String act_text = textMessage.getText();
				String exp_text = testDataobj.getPropertiesValue("act_msg");
				Assert.assertTrue(act_text.contains(exp_text));
				driver.switchTo().window(parent_window);
				Thread.sleep(2000);
			} 
		}

	}
	
	public String serviceMenuCheck() throws InterruptedException
	{
		serviceOption.click();
		Actions action = new Actions(driver);		
		explicitWait(menuOption);
		action.moveToElement(menuOption).build().perform();
		explicitWait(secondOptionFromDropdownMenu);
		action.moveToElement(secondOptionFromDropdownMenu).build().perform();
		String optionName = secondOptionFromDropdownMenu.getText();
		return optionName;
		
	}
	
	public String salesMenuCheck()
	{
		salesOption.click();
		UIControlAction ui = new UIControlAction(driver);
		explicitWait(menuOption);
		ui.moveToWebElement(menuOption);
		explicitWait(secondOptionFromDropdownSalesMenu);
		ui.moveToWebElement(secondOptionFromDropdownSalesMenu);
		String option = secondOptionFromDropdownSalesMenu.getText();
		return option;
	}

}
