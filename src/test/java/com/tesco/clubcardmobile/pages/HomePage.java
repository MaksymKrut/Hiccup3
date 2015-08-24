package com.tesco.clubcardmobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tesco.clubcardmobile.utils.Config;

public class HomePage extends Config{
	
	public static By burgerMenuButton = By.name("Open");
	
	public HomePage waitForBurgerMenuButton(){
		wdwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(burgerMenuButton));
		return new HomePage();
	}
	
	public HomePage clickOnBurgerMenuItem(String menuItemName) {
		// appiumhelpers.clickOnName(homepage.getBurgerMenuButton(), driver);
		driver.findElement(burgerMenuButton).click();
		wdwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By
				.id("drawerItem")));

		for (WebElement i : driver.findElementsById("drawerItem")) {
			if (i.getText().equalsIgnoreCase(menuItemName)) {
				i.click();
				break;
			}
		}
		return this;

	}
	
}
