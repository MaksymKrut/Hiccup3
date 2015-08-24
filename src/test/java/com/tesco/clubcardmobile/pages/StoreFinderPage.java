package com.tesco.clubcardmobile.pages;

import org.openqa.selenium.By;

public class StoreFinderPage {
	
	By searchField = By.id("txtAddress");

	/**
	 * @return the searchField
	 */
	public By getSearchField() {
		return searchField;
	}

}
