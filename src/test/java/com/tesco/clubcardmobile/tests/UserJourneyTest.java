package com.tesco.clubcardmobile.tests;

import java.io.IOException;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tesco.clubcardmobile.pages.CouponsPage;
import com.tesco.clubcardmobile.pages.HomePage;
import com.tesco.clubcardmobile.pages.ProfilePage;
import com.tesco.clubcardmobile.pages.SecureSignInPage;
import com.tesco.clubcardmobile.pages.SignInPage;
import com.tesco.clubcardmobile.pages.WelcomePage;
import com.tesco.clubcardmobile.utils.Config;
import com.tesco.clubcardmobile.utils.IConfig;
import com.tesco.clubcardmobile.utils.ServiceHandler;

/**
 * @author maheswaran.palanisamy
 *
 */
public class UserJourneyTest extends Config {

	IConfig config = new Config();
	WelcomePage welcomePage = PageFactory.initElements(driver,
			WelcomePage.class);
	SignInPage signInPage = PageFactory.initElements(driver, SignInPage.class);
	SecureSignInPage secureSignInPage = PageFactory.initElements(driver,
			SecureSignInPage.class);
	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
	CouponsPage couponsPage = PageFactory.initElements(driver,
			CouponsPage.class);
	ProfilePage profilePage = PageFactory.initElements(driver,
			ProfilePage.class);
	ServiceHandler serviceHandler = new ServiceHandler();

	// login->add coupon->verify at the till->remove coupon->verify at the
	// till->logout
	@Test(groups = { "sanity" })
	@Parameters({ "ENVIRONMENT", "USERNAME", "PASSWORD" })
	public void userJourney1(String env, String username, String password)
			throws ParseException, JSONException, IOException {
		// Steps to login
		welcomePage.clickOnSignInButton();
		signInPage.enterUsernameAndPassword(username, password);
		signInPage.clickOnSignInButton();
		secureSignInPage.enterClubcardNumber(serviceHandler.getClubcardNumber(
				env, username, password, 0));

		// Steps to add coupons add verify & remove verify
		homePage.clickOnBurgerMenuItem("Coupons");
		couponsPage.handleTopMessage();
		couponsPage.addCoupon(0, env, username, password);
		couponsPage.verifyCouponAddedAtTill(0);
		homePage.clickOnBurgerMenuItem("Coupons");
		couponsPage.removeCoupon(0, env, username, password);
		couponsPage.verifyCouponRemovedAtTill(0);

		// Steps to logout
		homePage.waitForBurgerMenuButton();
		homePage.clickOnBurgerMenuItem("Profile");
		profilePage.logout();
	}

}
