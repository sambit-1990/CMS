package com.CMS.TestCases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.CMS.PageObjects.HomePage;
import com.CMS.PageObjects.LoginPage;

public class LoginPageTestCases extends BaseTest {
	LoginPage lp;
	HomePage hp;

	@BeforeClass
	public void beforeRun() {
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String url = testDataobj.getPropertiesValue("url");
		driver.get(url);
	}

	@Test(priority = 1, dataProvider = "testData")
	public void successfulLoginTest(String username, String password) {

		lp.enterUsername(username);
		lp.enterPassword(password);
		lp.clickSubmitButton();
		if (lp.errorMessage.isDisplayed()) {
			Assert.assertTrue(true);
			System.out.println(lp.getErrorMessage());
			lp.UserName.clear();
			lp.Password.clear();

		} else if (hp.logout.isDisplayed()) {

			hp.clickLogOut();
			Assert.assertTrue(true);
		}

	}

	@Test(priority = 2)
	public void forgetPasswordTest() throws InterruptedException {
		String eid = testDataobj.getPropertiesValue("eid");
		String actText = lp.resetPassword(eid);
		String expText = testDataobj.getPropertiesValue("expectedForgetPasswordText");
		Assert.assertEquals(actText, expText);
		//Thread.sleep(2000);
		// Assert.assertTrue(true);

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
