package com.tesco.clubcardmobile.pages;

import java.io.IOException;

import org.apache.http.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tesco.clubcardmobile.utils.AppiumHelpers;
import com.tesco.clubcardmobile.utils.Config;
import com.tesco.clubcardmobile.utils.ServiceHandler;

public class CouponsPage extends Config {

	ServiceHandler serviceHandler = new ServiceHandler();
	AppiumHelpers appiumHelpers = new AppiumHelpers();

	public By couponDescription = By.id("description_lbl");
	public By addButton = By.id("btn_addRemove");
	public By topMessageOkButton = By.id("first_button");
	public By loadingProgress = By.id("LoadingProgress");

	HomePage homepage = PageFactory.initElements(driver, HomePage.class);

	/**
	 * Add coupon based on the couponNumber
	 */
	public void addCoupon(int couponNumber, String env, String username,
			String password) {
		String appCouponDescription = driver.findElements(couponDescription)
				.get(couponNumber).getText();
		System.out.println(appCouponDescription);
		if (getCouponState(env, username, password, appCouponDescription)
				.equalsIgnoreCase("DONOTAUTOMATICALLYUSE")) {
			driver.findElement(addButton).click();
		} else {
			driver.findElement(addButton).click();
			wdwait.until(ExpectedConditions
					.invisibilityOfElementLocated(loadingProgress));
			driver.findElement(addButton).click();
			wdwait.until(ExpectedConditions
					.invisibilityOfElementLocated(loadingProgress));
		}
	}

	/**
	 * Remove coupon based on the couponNumber
	 */
	public void removeCoupon(int couponNumber, String env, String username,
			String password) {
		String appCouponDescription = driver.findElements(couponDescription)
				.get(couponNumber).getText();
		System.out.println(appCouponDescription);
		if (getCouponState(env, username, password, appCouponDescription)
				.equalsIgnoreCase("AUTOMATICALLYUSE")) {
			driver.findElement(addButton).click();
		} else {
			driver.findElement(addButton).click();
			wdwait.until(ExpectedConditions
					.invisibilityOfElementLocated(loadingProgress));
			driver.findElement(addButton).click();
			wdwait.until(ExpectedConditions
					.invisibilityOfElementLocated(loadingProgress));
		}
	}

	public String getCouponState(String env, String username, String password,
			String appCouponDescription) {
		JSONObject apiCoupons = null;
		try {
			apiCoupons = serviceHandler.getCouponResponse(env, username,
					password);
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
		JSONArray apiCoupons1 = null;
		try {
			apiCoupons1 = apiCoupons.getJSONArray("CouponsList");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int size = apiCoupons1.length();
		System.out.println(apiCoupons1.length());
		System.out.println(apiCoupons1);
		String state = null;
		for (int i = 0; i < size; i++) {
			try {
				System.out.println(apiCoupons.getJSONArray("CouponsList")
						.get(i));
				System.out.println(apiCoupons.getJSONArray("CouponsList")
						.getJSONObject(i));
				String eachCoupon = apiCoupons.getJSONArray("CouponsList")
						.getJSONObject(i).get("state").toString();
				if (eachCoupon.equalsIgnoreCase("ISSUED")) {
					if (appCouponDescription.equalsIgnoreCase(apiCoupons
							.getJSONArray("CouponsList").getJSONObject(i)
							.get("description").toString())) {
						System.out.println("Got expected coupon from api "
								+ apiCoupons.getJSONArray("CouponsList")
										.getJSONObject(i).get("description")
										.toString());
						System.out
								.println("And its preference is "
										+ apiCoupons
												.getJSONArray("CouponsList")
												.getJSONObject(i)
												.get("redemptionPreference")
												.toString());
						state = apiCoupons.getJSONArray("CouponsList")
								.getJSONObject(i).get("redemptionPreference")
								.toString();
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return state;
	}

	public void verifyCouponAddedAtTill(int couponNumber) {
		String appCouponDescription = driver.findElements(couponDescription)
				.get(couponNumber).getText();
		homepage.clickOnBurgerMenuItem("At the till");
		try {
			if (driver.findElement(topMessageOkButton).isDisplayed()) {
				driver.findElement(By.name("Ok")).click();
			}
		} catch (Exception e) {
			System.out
					.println(e
							+ "Ok button is not displayed, Might be the coupons is already added");
		}
		String tillCouponDescription = driver.findElements(couponDescription)
				.get(couponNumber).getText();
		appiumHelpers.verifyActualExpectedIsEqual(appCouponDescription,
				tillCouponDescription);
		/*
		 * driver.findElements(getCouponDescription()).size();
		 * 
		 * String prevLastCoupon = null; boolean flag = true; while(flag){
		 * String currentLastCoupon =
		 * driver.findElements(getCouponDescription())
		 * .get(driver.findElements(getCouponDescription
		 * ()).size()-1).toString();
		 * 
		 * } for(WebElement i:driver.findElements(getCouponDescription())){
		 * if(appCouponDescription.equalsIgnoreCase(i.getText())){
		 * Assert.assertTrue(true); } }
		 */
	}

	public void verifyCouponRemovedAtTill(int couponNumber) {
		String appCouponDescription = driver.findElements(couponDescription)
				.get(couponNumber).getText();

		homepage.clickOnBurgerMenuItem("At the till");
		try {
			if (driver.findElement(topMessageOkButton).isDisplayed()) {
				driver.findElement(By.name("Ok")).click();
			}
		} catch (Exception e) {
			System.out
					.println(e
							+ "Ok button is not displayed, Might be the coupons is already added");
		}
		String tillCouponDescription = driver.findElements(couponDescription)
				.get(couponNumber).getText();
		appiumHelpers.verifyActualExpectedIsNotEqual(appCouponDescription,
				tillCouponDescription);
		/*
		 * driver.findElements(getCouponDescription()).size();
		 * 
		 * String prevLastCoupon = null; boolean flag = true; while(flag){
		 * String currentLastCoupon =
		 * driver.findElements(getCouponDescription())
		 * .get(driver.findElements(getCouponDescription
		 * ()).size()-1).toString();
		 * 
		 * } for(WebElement i:driver.findElements(getCouponDescription())){
		 * if(appCouponDescription.equalsIgnoreCase(i.getText())){
		 * Assert.assertTrue(true); } }
		 */
	}

	public Boolean visibilityOfTopMessageOkButton() {
		return driver.findElement(topMessageOkButton).isDisplayed();
	}
	
	public CouponsPage handleTopMessage(){
		try {
			if(visibilityOfTopMessageOkButton()){
				driver.findElement(By.name("Ok")).click();
			}
		} catch (Exception e) {
			System.out.println(e+"Ok button is not displayed, Might be the coupons is already added");			
		}
		return this;
	}

}
