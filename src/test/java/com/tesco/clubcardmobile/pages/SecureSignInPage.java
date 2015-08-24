package com.tesco.clubcardmobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tesco.clubcardmobile.utils.Config;

public class SecureSignInPage extends Config{
	public By promptCCNotext = By.id("digitsText");
	public By ccNoFirstField = By.id("character1");
	public By ccNoSecondField = By.id("character2");
	public By ccNoThirdField = By.id("character3");
	public By signInButton = By.id("signin");
	
	public SecureSignInPage enterClubcardNumber(String clubcardNo) {
		wdwait.until(ExpectedConditions
				.visibilityOfElementLocated(ccNoFirstField));
		String expectedCCNos = driver.findElement(promptCCNotext).getText();
		String[] expectedCCNos1 = expectedCCNos.split(" ");
		driver.findElement(ccNoFirstField).click();
		driver.findElement(ccNoFirstField).sendKeys(
				clubcardNo.split("")[Integer.valueOf(expectedCCNos1[3]
						.substring(0, 2)) - 1]);
		driver.findElement(ccNoSecondField).sendKeys(
				clubcardNo.split("")[Integer.valueOf(expectedCCNos1[4]) - 1]);
		driver.findElement(ccNoThirdField).sendKeys(
				clubcardNo.split("")[Integer.valueOf(expectedCCNos1[6]
						.substring(0, 2)) - 1]);
		return this;
	}
	
	public SecureSignInPage clickOnSignInbutton(){
		driver.findElement(signInButton).click();
		return this;
	}

}
