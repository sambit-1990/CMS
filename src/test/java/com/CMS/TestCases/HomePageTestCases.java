package com.CMS.TestCases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.CMS.PageObjects.HomePage;
import com.CMS.PageObjects.LoginPage;
import com.CMS.Utilities.UIControlAction;

public class HomePageTestCases extends BaseTest {

	HomePage hp;
	LoginPage lp;
	UIControlAction ui;

	@BeforeClass
	public void beforerun() {
		hp = new HomePage(driver);
		lp = new LoginPage(driver);
		ui = new UIControlAction(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String url = testDataobj.getPropertiesValue("url");
		String username = testDataobj.getPropertiesValue("login_username");
		String password = testDataobj.getPropertiesValue("login_password");
		driver.get(url);
		lp.enterUsername(username);
		lp.enterPassword(password);
		lp.clickSubmitButton();
	}
	
	@Test(priority=0)
	public void ginStockDataValidation() throws InterruptedException
	{
		hp.checkGinFreeStock();
	}
	
	@Test(priority=1)
	public void serviceMenuValidation() throws InterruptedException
	{
		String optionName = hp.serviceMenuCheck();
		hp.moduleLink.click();
		Thread.sleep(1000);
		String expOptionName = testDataobj.getPropertiesValue("exp_OptionName");
		Assert.assertTrue(optionName.contains(expOptionName));
		
	}
	
	@Test(priority=2)
	public void salesMenuValidation()
	{
		String actOption = hp.salesMenuCheck();
		String expOption = testDataobj.getPropertiesValue("exp_OptionsName");
		Assert.assertTrue(actOption.contains(expOption));
	}
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}

}
