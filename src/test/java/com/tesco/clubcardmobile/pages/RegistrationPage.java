package com.tesco.clubcardmobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.tesco.clubcardmobile.utils.AppiumHelpers;
import com.tesco.clubcardmobile.utils.Config;

public class RegistrationPage extends Config {
	WelcomePage welcomePage = PageFactory.initElements(driver,
			WelcomePage.class);
	AppiumHelpers appiumHelpers = new AppiumHelpers();

	public By emailAddressField = By.id("email_address");
	public By postcodeField = By.id("postcode");
	public By clubcardNumberField = By.id("clubcard_number");
	public By continueButton = By.id("continue_button");
	public By titleField = By.id("userprefix");
	public By firstNameField = By.id("first_name");
	public By surNameField = By.id("last_name");
	public By addressField = By.id("useraddresssuggestion");
	public By phoneNumberField = By.id("phone_number");
	public By passwordField = By.id("password_et");
	public By checkBox = By.id("acceptcondition");
	public By registerButton = By.id("reg_btn");

	public void enterDetailsInFirstScreen() {
		wdwait.until(ExpectedConditions
				.visibilityOfElementLocated(welcomePage.signInButton));
		appiumHelpers.clickOnID(welcomePage.registerButton);
		appiumHelpers.clickOnID(emailAddressField);
		appiumHelpers.enterTextById(emailAddressField, "james.bondd@gmail.com");
		appiumHelpers.clickOnID(postcodeField);
		appiumHelpers.enterTextById(postcodeField, "pl99uy");
		driver.scrollTo("OR SIGN IN IF YOU HAVE AN ACCOUNT");
		appiumHelpers.clickOnID(clubcardNumberField);
		appiumHelpers.enterTextById(clubcardNumberField, "6340040000000143");
	}

	public void enterDetailsInSecondScreen() {
		wdwait.until(ExpectedConditions.visibilityOfElementLocated(titleField));
		// appiumHelpers.selectItemFromDropDown(getTitleField(), "Mr");
		// appiumHelpers.clickOnID(getTitleField());
		// driver.scrollToExact("Mr");
		// appiumHelpers.clickOnName(By.name("Mr"));
		// appiumHelpers.clickOnID(firstNameField);
		appiumHelpers.enterTextById(firstNameField, "James");
		appiumHelpers.enterTextById(surNameField, "Bond");
		// appiumHelpers.enterTextById(firstNameField, "James");
		// appiumHelpers.selectItemFromDropDown(addressField,
		// "2 Parsons Close");
		// appiumHelpers.clickOnID(addressField);
		// driver.scrollToExact("2 Parsons Close");
		driver.scrollTo("REGISTER");
		appiumHelpers.enterTextById(phoneNumberField, "07498653219");
		appiumHelpers.enterTextById(passwordField, "Password+1");
		appiumHelpers.clickOnID(checkBox);
		driver.hideKeyboard();
	}

}
