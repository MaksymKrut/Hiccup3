package com.tesco.clubcardmobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tesco.clubcardmobile.utils.Config;

public class WelcomePage extends Config {

	public By signInButton = By.id("btn_sign_in");
	public By registerButton = By.id("btn_sign_up");
	public By noConnectionText = By.name("NO CONNECTION");

	// @FindBy(id="btn_sign_in") private WebElement signInButton;

	public WelcomePage waitForSignInButton() {
		wdwait.until(ExpectedConditions
				.visibilityOfElementLocated(signInButton));
		return this;
	}

	public WelcomePage clickOnSignInButton() {
		driver.findElement(signInButton).click();
		return this;
	}

	public WelcomePage clickOnRegisterButton() {
		driver.findElement(registerButton).click();
		return this;
	}

	public WelcomePage clickOnNoConnectionText() {
		driver.findElement(noConnectionText).click();
		return this;
	}

}
