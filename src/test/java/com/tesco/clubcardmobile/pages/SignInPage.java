package com.tesco.clubcardmobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tesco.clubcardmobile.utils.Config;

public class SignInPage extends Config {

	public By emailAddressField = By.id("username_et");
	public By passwordField = By.id("password_et");
	public By signInButton = By.id("signin_btn");
	
	
	public SignInPage enterUsernameAndPassword(String username, String password){
		wdwait.until(ExpectedConditions.visibilityOfElementLocated(emailAddressField));
		driver.findElement(emailAddressField).click();
		driver.findElement(emailAddressField)
				.sendKeys(username);
		driver.findElement(passwordField).sendKeys(password);
		return this;
	}
	
	public SignInPage clickOnSignInButton(){
		driver.findElement(signInButton).click();
		return this;
	}
}
