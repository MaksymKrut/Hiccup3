package com.tesco.clubcardmobile.pages;

import org.openqa.selenium.By;

public class FAQPage {
	
	By magnifyButton = By.id("search_btn");
	By searchField = By.id("btn_search");

	/**
	 * @return the searchField
	 */
	public By getSearchField() {
		return searchField;
	}

	/**
	 * @return the magnifyButton
	 */
	public By getMagnifyButton() {
		return magnifyButton;
	}

}
