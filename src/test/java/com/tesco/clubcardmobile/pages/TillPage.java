package com.tesco.clubcardmobile.pages;

import org.openqa.selenium.By;

public class TillPage {
	
	By couponDescription = By.id("description_lbl");
	By addButton = By.id("btn_addRemove");
	
	public By getCouponDescription() {
		return couponDescription;
	}
	
	public By getAddButton() {
		return addButton;
	}

}
