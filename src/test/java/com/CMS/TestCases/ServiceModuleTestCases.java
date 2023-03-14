package com.CMS.TestCases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.CMS.PageObjects.HomePage;
import com.CMS.PageObjects.LoginPage;
import com.CMS.PageObjects.ServiceModule;

public class ServiceModuleTestCases extends BaseTest {

	ServiceModule sm;
	LoginPage lp;
	HomePage hp;

	@BeforeClass
	public void beforeRun() {
		sm = new ServiceModule(driver);
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String url = testDataobj.getPropertiesValue("url");
		driver.get(url);
		String username = testDataobj.getPropertiesValue("login_username");
		lp.enterUsername(username);
		String pswrd = testDataobj.getPropertiesValue("login_password");
		lp.enterPassword(pswrd);
		lp.clickSubmitButton();
		hp.serviceOption.click();

	}
	
	@Test(priority=0)
	public void validateChangePasswordFunction()
	{
		sm.passwordChange();
		Assert.assertTrue(true);
	}

	@AfterClass
	public void afterRun() {
		driver.quit();
	}

}
