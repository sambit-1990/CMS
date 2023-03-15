package com.CMS.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.CMS.TestCases.BaseTest;
import com.CMS.Utilities.UIControlAction;
import com.CMS.Utilities.WaitUtilities;

public class ServiceModule extends BaseTest {

	WebDriver driver;
	UIControlAction ui;
	WaitUtilities wu;

	public ServiceModule(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@title='Menu']")
	WebElement menuOption;

	@FindBy(xpath = "//li[@title='Change Pwd']")
	WebElement changePasswordOption;

	@FindBy(id = "ctl00_ContentPlaceHolder1_ASPxTextBoxNewPassword_I")
	WebElement newPasswordoption;

	@FindBy(id = "ctl00_ContentPlaceHolder1_ASPxTextBoxRetypePassword_I")
	WebElement reTypePasswordOption;

	@FindBy(id = "ctl00_ContentPlaceHolder1_ASPxButtonSubmit")
	WebElement submitButton;

	@FindBy(id = "ctl00_ContentPlaceHolder1_ASPxPopupControlPwdInfo_LabelPwdInfo")
	WebElement errorMessage;

	public void passwordChange() {
		ui = new UIControlAction(driver);
		wu = new WaitUtilities(driver);
		wu.WaitExplicitly(menuOption);
		ui.moveToWebElement(menuOption);
		wu.WaitExplicitly(changePasswordOption);
		ui.moveToWebElement(changePasswordOption);
		changePasswordOption.click();
		String newPassword = testDataobj.getPropertiesValue("New_Password");
		newPasswordoption.sendKeys(newPassword);
		String confPassword = testDataobj.getPropertiesValue("Confirm_Password");
		reTypePasswordOption.sendKeys(confPassword);
		ui.moveToWebElement(submitButton);
		newPasswordoption.clear();
		reTypePasswordOption.clear();
	}

	public void newPasswordFieldCheck(String newPassword, String confirmNewPassword) {
		ui = new UIControlAction(driver);
		wu = new WaitUtilities(driver);
		
		/*wu.WaitExplicitly(menuOption);
		ui.moveToWebElement(menuOption);
		wu.WaitExplicitly(changePasswordOption);
		ui.moveToWebElement(changePasswordOption);
		changePasswordOption.click();*/
		newPasswordoption.sendKeys(newPassword);
		reTypePasswordOption.sendKeys(confirmNewPassword);
		submitButton.click();
		String actErrorMessage = errorMessage.getText();
		System.out.println(actErrorMessage);
		String expErrorMessage = testDataobj.getPropertiesValue("Exp_Error_Message_SpCh");
		System.out.println(expErrorMessage);
		if (actErrorMessage.equalsIgnoreCase(expErrorMessage)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

	}

}
