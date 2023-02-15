package com.CMS.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.CMS.PageObjects.HomePage;
import com.CMS.PageObjects.LoginPage;

//Validate logging into the Application using valid credentials
public class TC_LF_002 extends BaseTest {

	@Test(dataProvider = "testData")
	public void successfulLoginTest(String username, String password) {
		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		lp.goToLoginPage();
		lp.enterUsername(username);
		lp.enterPassword(password);
		lp.clickSubmitButton();
		String actPageTitle = hp.getPageTitle();
		String expPageTitle = "Welcome to Grundfos";
		Assert.assertTrue(actPageTitle.equalsIgnoreCase(expPageTitle));

	}
}
