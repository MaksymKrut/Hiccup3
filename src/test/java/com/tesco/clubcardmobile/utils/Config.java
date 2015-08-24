package com.tesco.clubcardmobile.utils;

import org.testng.Assert;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

//import com.github.genium_framework.appium.support.server.AppiumServer;
//import com.github.genium_framework.server.ServerArguments;
import com.tesco.clubcardmobile.pages.HomePage;
import com.tesco.clubcardmobile.pages.SignInPage;
import com.tesco.clubcardmobile.pages.WelcomePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author maheswaran.palanisamy
 *
 */

public class Config extends ServiceHandler implements IConfig{

	public static AndroidDriver driver;
	static Logger log = Logger.getLogger(Config.class);
	public static WebDriverWait wdwait;

	// AppiumServer appiumServer = null;
	// AppiumHelpers appiumhelpers = new AppiumHelpers();

	// Launching Driver with the desired capabilities
	public AndroidDriver Selectingdriver(@Optional("Android") String driver_name)
			throws MalformedURLException {
		if (driver_name.equals("Android")) {
			File app = new File(System.getProperty("user.dir") + File.separator
					+ "src" + File.separator + "test" + File.separator
					+ "resources" + File.separator + "app").listFiles()[0];
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformVersion", "5.0");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("deviceName", "SM-G900F");
			capabilities.setCapability("app", app.getAbsolutePath());
			capabilities
					.setCapability("appPackage", "com.tesco.clubcardmobile");
			capabilities.setCapability("app-wait-activity", "WelcomeActivity");
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
					capabilities);
		} /*
		 * else if (driver_name.equals("iOS")) { File app = new
		 * File(System.getProperty("user.dir") + File.separator +
		 * "app").listFiles()[0]; DesiredCapabilities capabilities = new
		 * DesiredCapabilities(); capabilities.setCapability("platformName",
		 * "iOS"); capabilities.setCapability("deviceName", "iPhone"); //
		 * capabilities.setCapability("browserName", "Safari");
		 * capabilities.setCapability("udid",
		 * "de8fc3a7729bcc020cd4136a57c1e84f75f4110a");
		 * capabilities.setCapability("bundleid", "com.tesco.clubcardmobile");
		 * capabilities.setCapability("app", app.getAbsolutePath()); driver =
		 * new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		 * 
		 * }
		 */
		return driver;
	}

	// Initiate driver capabilities
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "DRIVER_NAME" })
	public AppiumDriver setUp(String driver_name) {
		try {
			driver = Selectingdriver(driver_name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Driver Launched");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wdwait = new WebDriverWait(driver, 30);
		return driver;
	}

	// Closing the driver
	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		try {
			driver.quit();
			log.info("Execution successful : Driver Quit");
		} catch (Exception e) {
			log.error("TearDown Failed", e);
			Assert.fail("TearDown Failed", e);
		}
	}

	public void wifi(String state) {

		if (!((AndroidDriver) driver).getNetworkConnection().wifiEnabled()
				&& state.equalsIgnoreCase("enable")) {
			NetworkConnectionSetting WifiEnablesetting = new NetworkConnectionSetting(
					false, true, false);
			((AndroidDriver) driver).setNetworkConnection(WifiEnablesetting);

		} else if (state.equalsIgnoreCase("disable")) {
			NetworkConnectionSetting WifiDisablesetting = new NetworkConnectionSetting(
					false, false, false);
			((AndroidDriver) driver).setNetworkConnection(WifiDisablesetting);
		}
	}

	public void verifyWifiAndEnable() {
		if (!((AndroidDriver) driver).getNetworkConnection().wifiEnabled()) {
			NetworkConnectionSetting WifiEnablesetting = new NetworkConnectionSetting(
					false, true, false);
			((AndroidDriver) driver).setNetworkConnection(WifiEnablesetting);
		}
	}


	/*
	 * public void appiumServerStart() { try {
	 * System.out.println("Appium Start"); ServerArguments serverArguments = new
	 * ServerArguments(); serverArguments.setArgument("--address", "127.0.0.1");
	 * //serverArguments.setArgument("--chromedriver-port", 9516);
	 * //serverArguments.setArgument("--bootstrap-port", 4725);
	 * serverArguments.setArgument("--port", 4723);
	 * serverArguments.setArgument("--no-reset", true);
	 * serverArguments.setArgument("--local-timezone", true); appiumServer = new
	 * AppiumServer(serverArguments); appiumServer.startServer(); //
	 * System.out.println
	 * ("is appium server running -"+appiumServer.isServerRunning());
	 * 
	 * }catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * public void appiumServerStop() { appiumServer.stopServer();
	 * 
	 * }
	 */

	/*
	 * private static Process process; private static String APPIUMSERVERSTART =
	 * "node /Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js"
	 * ;
	 * 
	 * public static void startAppiumServer() throws IOException,
	 * InterruptedException { Runtime runtime = Runtime.getRuntime(); process =
	 * runtime.exec(APPIUMSERVERSTART); Thread.sleep(5000); if (process != null)
	 * { System.out.println("Appium server started"); } }
	 * 
	 * public static void stopAppiumServer() throws IOException { if (process !=
	 * null) { process.destroy(); } System.out.println("Appium server stop"); }
	 */
}
