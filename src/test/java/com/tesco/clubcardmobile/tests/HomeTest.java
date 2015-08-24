package com.tesco.clubcardmobile.tests;

import java.io.IOException;
import java.util.List;

import org.apache.http.ParseException;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import com.tesco.clubcardmobile.pages.SignInPage;
import com.tesco.clubcardmobile.pages.WelcomePage;
import com.tesco.clubcardmobile.utils.AppiumHelpers;
import com.tesco.clubcardmobile.utils.Config;
import com.tesco.clubcardmobile.utils.IConfig;
import com.tesco.clubcardmobile.utils.ServiceHandler;

import io.appium.java_client.AppiumDriver;

/**
 * @author maheswaran.palanisamy
 *
 */
public class HomeTest extends Config{
	AppiumDriver driver;
	
	/// Create the objects which are required for login test
	IConfig config = new Config();
	WelcomePage welcomePage = PageFactory.initElements(driver, WelcomePage.class);
	SignInPage signInPage = PageFactory.initElements(driver, SignInPage.class);
	ServiceHandler sh = new ServiceHandler();
	AppiumHelpers ah = new AppiumHelpers();
	static Logger log = Logger.getLogger(HomeTest.class);
	
	@BeforeClass
	@Parameters({"DRIVER_NAME"})
	public AppiumDriver setUp(String driver_name){
		log.info("Started initiating driver");
		driver = config.setUp(driver_name);
		System.out.println("driver launched");
		return driver;
	}
	
	@AfterClass
	public void tearDown(){
		config.tearDown();
	}
	
}
