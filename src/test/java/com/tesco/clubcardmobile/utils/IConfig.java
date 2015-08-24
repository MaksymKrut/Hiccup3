package com.tesco.clubcardmobile.utils;

import io.appium.java_client.AppiumDriver;



public interface IConfig {

	public AppiumDriver setUp(String driver_name);
	public void tearDown();
	public void wifi(String state);
	public void verifyWifiAndEnable();
	//public void appiumServerStart();
	//public void appiumServerStop();
		
}
