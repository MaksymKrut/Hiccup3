package com.tesco.clubcardmobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tesco.clubcardmobile.utils.Config;

public class ProfilePage extends Config{
	
	public ProfilePage logout() {
		driver.scrollTo("Sign out");
		driver.findElement(By.name("Sign out")).click();
		wdwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By
				.name("Ok")));
		driver.findElement(By.name("Ok")).click();
		return this;
	}

}
