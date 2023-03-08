package com.CMS.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	
	//String employeeID = testDataobj.getPropertiesValue("employeeID");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "Login1_UserName")
	public WebElement UserName;

	@FindBy(id = "Login1_Password")
	public WebElement Password;

	@FindBy(xpath = "(//input[@type='submit'])[1]")
	WebElement submitButton;

	@FindBy(xpath = "//td[@align='center']")
	public WebElement errorMessage;

	@FindBy(id = "Login1_PasswordRecoveryLink")
	WebElement forgetPassword;

	@FindBy(id = "ctl00_ContentPlaceHolder1_RadioButtonEmp")
	WebElement empRadiobtn;

	@FindBy(id = "ctl00_ContentPlaceHolder1_ButtonNext")
	WebElement nextbtn;

	@FindBy(id = "ctl00_ContentPlaceHolder1_TextBoxEmpUserName")
	WebElement usernameText;

	@FindBy(xpath = "//div[contains(text(),'Enter your Login ID to receive your password in mail')]")
	WebElement finalText;


	public void enterUsername(String username) {
		UserName.sendKeys(username);
	}

	public void enterPassword(String password) {
		Password.sendKeys(password);
	}

	public void clickSubmitButton() {
		submitButton.click();
	}

	public String getErrorMessage() {
		return errorMessage.getText();
	}

	public void clickForgetPassword() {
		forgetPassword.click();
	}

	public void selectEmployee() {
		empRadiobtn.click();
	}

	public void clickNext() {
		nextbtn.click();
	}

	public void enterusername(String employeeID) {
		usernameText.sendKeys(employeeID);
	}

	public String getFinalText() {
		return finalText.getText();
	}
	
	public String resetPassword(String username)
	{
		clickForgetPassword();
		selectEmployee();
		clickNext();
		enterusername(username);
		String finalText = getFinalText();
		return finalText;
		
		
	}

}
