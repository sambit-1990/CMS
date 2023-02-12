package com.CMS.TestCases;

import org.testng.annotations.Test;
import com.CMS.PageObjects.LoginPage;

public class TC_LF_001 extends BaseTest {

	@Test
	public void openLandingPage() {

		LoginPage lp = new LoginPage(driver);
		lp.goToLoginPage();

	}

}
