package com.tesco.clubcardmobile.tests;

import java.awt.event.KeyEvent;
import java.io.IOException;

import io.appium.java_client.android.AndroidKeyCode;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tesco.clubcardmobile.pages.CouponsPage;
import com.tesco.clubcardmobile.pages.FAQPage;
import com.tesco.clubcardmobile.pages.HomePage;
import com.tesco.clubcardmobile.pages.RegistrationPage;
import com.tesco.clubcardmobile.pages.SecureSignInPage;
import com.tesco.clubcardmobile.pages.SignInPage;
import com.tesco.clubcardmobile.pages.StoreFinderPage;
import com.tesco.clubcardmobile.pages.WelcomePage;
import com.tesco.clubcardmobile.utils.AppiumHelpers;
import com.tesco.clubcardmobile.utils.Config;
import com.tesco.clubcardmobile.utils.IConfig;
import com.tesco.clubcardmobile.utils.ServiceHandler;

public class NoNetworkTest extends Config {

	IConfig config = new Config();
	CouponsPage couponsPage = PageFactory.initElements(driver,
			CouponsPage.class);
	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
	WelcomePage welcomePage = PageFactory.initElements(driver,
			WelcomePage.class);
	RegistrationPage registrationPage = PageFactory.initElements(driver,
			RegistrationPage.class);
	SignInPage signInPage = PageFactory.initElements(driver, SignInPage.class);
	SecureSignInPage secureSignInPage = PageFactory.initElements(driver,
			SecureSignInPage.class);
	StoreFinderPage storeFinderPage = PageFactory.initElements(driver,
			StoreFinderPage.class);
	FAQPage faqPage = PageFactory.initElements(driver, FAQPage.class);
	AppiumHelpers appiumHelpers = new AppiumHelpers();
	ServiceHandler serviceHandler = new ServiceHandler();

	@Test(priority = 1)
	public void verifyRegistrationFirstScreen() {
		registrationPage.enterDetailsInFirstScreen();
		config.wifi("disable");
		appiumHelpers.verifyTextPresent("NO CONNECTION");
		appiumHelpers.clickOnName(welcomePage.noConnectionText);
		appiumHelpers.verifyTextNotPresent("NO CONNECTION");
		appiumHelpers.clickOnID(registrationPage.continueButton);
		appiumHelpers.verifyTextPresent("NO CONNECTION");
		config.wifi("enable");
		appiumHelpers.clickOnID(registrationPage.continueButton);
		appiumHelpers.verifyElementPresentById(registrationPage.titleField);
	}

	@Test(priority = 2)
	public void verifyRegistrationSecondScreen() {
		registrationPage.enterDetailsInFirstScreen();
		appiumHelpers.clickOnID(registrationPage.continueButton);
		registrationPage.enterDetailsInSecondScreen();
		config.wifi("disable");
		appiumHelpers.verifyTextPresent("NO CONNECTION");
		appiumHelpers.clickOnName(welcomePage.noConnectionText);
		appiumHelpers.verifyTextNotPresent("NO CONNECTION");
		appiumHelpers.clickOnID(registrationPage.registerButton);
		appiumHelpers.verifyTextPresent("NO CONNECTION");
		config.wifi("enable");
		appiumHelpers.clickOnID(registrationPage.registerButton);
		appiumHelpers.verifyElementPresentById(registrationPage.titleField);
	}

	@Test(priority = 3)
	@Parameters({ "USERNAME", "PASSWORD" })
	public void verifySignInScreen(String username, String password) {
		welcomePage.waitForSignInButton();
		config.verifyWifiAndEnable();
		signInPage.enterUsernameAndPassword(username, password);
		config.wifi("disable");
		appiumHelpers.verifyTextPresent("NO CONNECTION");
		appiumHelpers.clickOnName(welcomePage.noConnectionText);
		appiumHelpers.verifyTextNotPresent("NO CONNECTION");
		appiumHelpers.clickOnID(signInPage.signInButton);
		appiumHelpers.verifyTextPresent("NO CONNECTION");
		config.wifi("enable");
		appiumHelpers.clickOnID(signInPage.signInButton);
		wdwait.until(ExpectedConditions
				.visibilityOfElementLocated(secureSignInPage.ccNoFirstField));
		appiumHelpers.verifyElementPresentById(secureSignInPage.ccNoFirstField);

	}

	@Test(priority = 4)
	@Parameters({ "ENVIRONMENT", "USERNAME", "PASSWORD" })
	public void verifySecureSignInScreen(String env, String username,
			String password) {
		wdwait.until(ExpectedConditions
				.visibilityOfElementLocated(welcomePage.signInButton));
		config.verifyWifiAndEnable();
		signInPage.enterUsernameAndPassword(username, password);
		appiumHelpers.clickOnID(signInPage.signInButton);
		try {
			secureSignInPage.enterClubcardNumber(serviceHandler
					.getClubcardNumber(env, username, password, 0));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		config.wifi("disable");
		appiumHelpers.verifyTextPresent("NO CONNECTION");
		appiumHelpers.clickOnName(welcomePage.noConnectionText);
		appiumHelpers.verifyTextNotPresent("NO CONNECTION");
		wdwait.until(ExpectedConditions
				.visibilityOfElementLocated(secureSignInPage.signInButton));
		appiumHelpers.clickOnID(secureSignInPage.signInButton);
		appiumHelpers.verifyTextNotPresent("HOME");
		config.wifi("enable");
		try {
			secureSignInPage.enterClubcardNumber(serviceHandler
					.getClubcardNumber(env, username, password, 0));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		appiumHelpers.clickOnID(secureSignInPage.signInButton);
		wdwait.until(ExpectedConditions.visibilityOfElementLocated(By
				.name("Home")));
		appiumHelpers.verifyTextPresent("Home");
	}

	@Test(priority = 5)
	@Parameters({ "ENVIRONMENT", "USERNAME", "PASSWORD" })
	public void verifyHomeScreen(String env, String username, String password)
			throws ParseException, JSONException, IOException {
		config.verifyWifiAndEnable();
		welcomePage.clickOnSignInButton();
		signInPage.enterUsernameAndPassword(username, password);
		signInPage.clickOnSignInButton();
		secureSignInPage.enterClubcardNumber(serviceHandler.getClubcardNumber(
				env, username, password, 0));
		appiumHelpers.verifyTextPresent("Home");
		config.wifi("disable");
		appiumHelpers.verifyTextPresent("NO CONNECTION");
		appiumHelpers.clickOnName(welcomePage.noConnectionText);
		appiumHelpers.verifyTextNotPresent("NO CONNECTION");
		homePage.clickOnBurgerMenuItem("Offers");
		homePage.clickOnBurgerMenuItem("Home");
		appiumHelpers.verifyTextPresent("NO CONNECTION");
		config.wifi("enable");
		appiumHelpers.verifyTextNotPresent("NO CONNECTION");
	}

	@Test(priority = 6)
	@Parameters({ "ENVIRONMENT", "USERNAME", "PASSWORD" })
	public void verifyStoreFinderScreen(String env, String username,
			String password) throws ParseException, JSONException, IOException {
		config.verifyWifiAndEnable();
		welcomePage.clickOnSignInButton();
		signInPage.enterUsernameAndPassword(username, password);
		signInPage.clickOnSignInButton();
		secureSignInPage.enterClubcardNumber(serviceHandler.getClubcardNumber(
				env, username, password, 0));
		appiumHelpers.verifyTextPresent("Home");
		homePage.clickOnBurgerMenuItem("Find a store");
		config.wifi("disable");
		appiumHelpers.verifyTextPresent("NO CONNECTION");
		appiumHelpers.clickOnName(welcomePage.noConnectionText);
		appiumHelpers.verifyTextNotPresent("NO CONNECTION");
		appiumHelpers.enterTextById(storeFinderPage.getSearchField(), "Test");
		// driver.getKeyboard().pressKey("Search");
		driver.sendKeyEvent(KeyEvent.VK_ENTER);
		appiumHelpers.verifyTextPresent("NO CONNECTION");
		config.wifi("enable");
		appiumHelpers.verifyTextNotPresent("NO CONNECTION");
	}

	@Test(priority = 7)
	@Parameters({ "ENVIRONMENT", "USERNAME", "PASSWORD" })
	public void verifyProfileScreen(String env, String username, String password)
			throws ParseException, JSONException, IOException {
		config.verifyWifiAndEnable();
		welcomePage.clickOnSignInButton();
		signInPage.enterUsernameAndPassword(username, password);
		signInPage.clickOnSignInButton();
		secureSignInPage.enterClubcardNumber(serviceHandler.getClubcardNumber(
				env, username, password, 0));
		appiumHelpers.verifyTextPresent("Home");
		homePage.clickOnBurgerMenuItem("Profile");
		config.wifi("disable");
		appiumHelpers.verifyTextPresent("NO CONNECTION");
		config.wifi("enable");
		appiumHelpers.verifyTextNotPresent("NO CONNECTION");
	}

	@Test(priority = 8)
	@Parameters({ "ENVIRONMENT", "USERNAME", "PASSWORD" })
	public void verifyCouponsScreen(String env, String username, String password)
			throws ParseException, JSONException, IOException {
		config.verifyWifiAndEnable();
		welcomePage.clickOnSignInButton();
		signInPage.enterUsernameAndPassword(username, password);
		signInPage.clickOnSignInButton();
		secureSignInPage.enterClubcardNumber(serviceHandler.getClubcardNumber(
				env, username, password, 0));
		appiumHelpers.verifyTextPresent("Home");
		homePage.clickOnBurgerMenuItem("Coupons");
		config.wifi("disable");
		appiumHelpers.verifyTextPresent("NO CONNECTION");
		config.wifi("enable");
		appiumHelpers.verifyTextNotPresent("NO CONNECTION");
	}

	@Test(priority = 9)
	@Parameters({ "ENVIRONMENT", "USERNAME", "PASSWORD" })
	public void verifyOffersScreen(String env, String username, String password)
			throws ParseException, JSONException, IOException {
		config.verifyWifiAndEnable();
		welcomePage.clickOnSignInButton();
		signInPage.enterUsernameAndPassword(username, password);
		signInPage.clickOnSignInButton();
		secureSignInPage.enterClubcardNumber(serviceHandler.getClubcardNumber(
				env, username, password, 0));
		appiumHelpers.verifyTextPresent("Home");
		homePage.clickOnBurgerMenuItem("Offers");
		config.wifi("disable");
		appiumHelpers.verifyTextPresent("NO CONNECTION");
		config.wifi("enable");
		appiumHelpers.verifyTextNotPresent("NO CONNECTION");
	}

	@Test(priority = 10)
	@Parameters({ "ENVIRONMENT", "USERNAME", "PASSWORD" })
	public void verifyTillScreen(String env, String username, String password)
			throws ParseException, JSONException, IOException {
		config.verifyWifiAndEnable();
		welcomePage.clickOnSignInButton();
		signInPage.enterUsernameAndPassword(username, password);
		signInPage.clickOnSignInButton();
		secureSignInPage.enterClubcardNumber(serviceHandler.getClubcardNumber(
				env, username, password, 0));
		appiumHelpers.verifyTextPresent("Home");
		homePage.clickOnBurgerMenuItem("At the till");
		config.wifi("disable");
		appiumHelpers.verifyTextPresent("NO CONNECTION");
		config.wifi("enable");
		appiumHelpers.verifyTextNotPresent("NO CONNECTION");
	}

	@Test(priority = 11)
	@Parameters({ "ENVIRONMENT", "USERNAME", "PASSWORD" })
	public void verifyFAQScreen(String env, String username, String password)
			throws ParseException, JSONException, IOException {
		config.verifyWifiAndEnable();
		welcomePage.clickOnSignInButton();
		signInPage.enterUsernameAndPassword(username, password);
		signInPage.clickOnSignInButton();
		secureSignInPage.enterClubcardNumber(serviceHandler.getClubcardNumber(
				env, username, password, 0));
		appiumHelpers.verifyTextPresent("Home");
		homePage.clickOnBurgerMenuItem("FAQ");
		config.wifi("disable");
		appiumHelpers.verifyTextPresent("NO CONNECTION");
		config.wifi("enable");
		appiumHelpers.verifyTextNotPresent("NO CONNECTION");
		config.wifi("disable");
		appiumHelpers.clickOnID(faqPage.getMagnifyButton());
		appiumHelpers.enterTextById(faqPage.getSearchField(), "Test");
		// driver.executeScript("mobile: key_event", 84);
		driver.sendKeyEvent(84);
		System.out.println("search");
		driver.sendKeyEvent(AndroidKeyCode.ENTER);
		// driver.sendKeyEvent(key, metastate);

		appiumHelpers.verifyTextPresent("NO CONNECTION");
		config.wifi("enable");
		appiumHelpers.verifyTextNotPresent("NO CONNECTION");
	}

}
