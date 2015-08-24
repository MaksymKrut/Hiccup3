package com.tesco.clubcardmobile.utils;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AppiumHelpers extends Config {

	WebDriverWait wdwait = null;

	public void clickOnID(By id) {
		driver.findElement(id).click();

	}

	public void clickOnXpath(By xpath) {
		driver.findElement(xpath).click();

	}

	public void clickOnClassname(By classname) {
		driver.findElement(classname).click();

	}

	public void clickOnName(By name) {
		driver.findElement(name).click();

	}

	public void enterTextById(By id, String text) {
		driver.findElement(id).click();
		driver.findElement(id).sendKeys(text);

	}

	public void enterTextByXpath(By xpath, String text) {
		driver.findElement(xpath).click();
		driver.findElement(xpath).sendKeys(text);

	}

	public void enterTextByClassname(By classname, String text) {
		driver.findElement(classname).click();
		driver.findElement(classname).sendKeys(text);

	}

	public void waitForVisibilityOfElementBy(By locator) {
		wdwait = new WebDriverWait(driver, 30);
		wdwait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(locator));
	}

	public void verifyActualExpectedIsEqual(String actual, String expected) {
		Assert.assertEquals(actual, expected);

	}

	public void verifyActualExpectedIsNotEqual(String actual, String expected) {
		Assert.assertNotEquals(actual, expected);

	}

	public void verifyActualExpectedIsEqual(String[] actual, String[] expected) {
		if (actual.length <= expected.length) {
			for (int i = 0; i < actual.length; i++) {
				Assert.assertEquals(actual[i], expected[i]);
			}
		} else if (expected.length <= actual.length) {
			for (int i = 0; i < expected.length; i++) {
				Assert.assertEquals(actual[i], expected[i]);
			}
		}

	}

	public void verifyTextPresent(String text) {
		Assert.assertTrue(driver.getPageSource().contains(text), "Expected "
				+ text + " is not present");
	}

	public void verifyTextNotPresent(String text) {
		Assert.assertFalse(driver.getPageSource().contains(text), "Expected "
				+ text + " is present");
	}

	public void verifyElementPresentById(By id) {
		Assert.assertTrue(driver.findElement(id).isDisplayed(), "Expected "
				+ id + " is not present");
	}

	// Need to write functions for scrolldown, scrollup
	public void scrollUntilVisibilityOf(String visibilityText) {
		driver.scrollTo(visibilityText);
	}

	public void scrollExactVisibilityOf(String visibilityText) {
		driver.scrollToExact(visibilityText);
	}
	
	public void selectItemFromDropDown(By id, String selectItem){
		clickOnID(id);
		//clickOnName(selectItem);
		//driver.scrollToExact(selectItem);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ele = driver.findElementById("spinnerTarget").getText();
		driver.findElement(By.name(ele));
		
	}

}
