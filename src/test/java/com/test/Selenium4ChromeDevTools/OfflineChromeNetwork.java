package com.test.Selenium4ChromeDevTools;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.network.Network;
import org.openqa.selenium.devtools.network.model.ConnectionType;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OfflineChromeNetwork {

	WebDriver driver;
	DevTools devtool;
	ArrayList<String> list=new ArrayList<String>();
	
	@Test
	public void verifyOfflineNetwork()
	{
		WebDriverManager.chromedriver().setup();
		
		driver=new ChromeDriver();
		devtool=((ChromeDriver)driver).getDevTools();
		devtool.createSession();
		
		emulateChromeNetwork(true);
		
		devtool.addListener(Network.loadingFailed(), log -> list.add(log.getErrorText()));
		System.out.println(list);
		
		driver.manage().window().maximize();
		driver.get("https://github.com/SeleniumHQ/selenium/blob/master/java/CHANGELOG");
		
		System.out.println(list);
		
		try
		{
			Thread.sleep(3000);
		}catch(Exception e)
		{
			
		}
		assertEquals(list.get(0), "net::ERR_INTERNET_DISCONNECTED");
		driver.close();
	}
	
	
	@Test
	public void verifyOnlineAndOfflineNetwork()
	{
		WebDriverManager.chromedriver().setup();		
		driver=new ChromeDriver();
		devtool=((ChromeDriver)driver).getDevTools();
		devtool.createSession();
		
		devtool.addListener(Network.loadingFailed(), log -> list.add(log.getErrorText()));
		emulateChromeNetwork(false);	
		driver.manage().window().maximize();
		driver.get("https://github.com/SeleniumHQ/selenium/blob/master/java/CHANGELOG");
		System.out.println(driver.getTitle());
		try
		{
			Thread.sleep(3000);
		}catch(Exception e)
		{
			
		}
		assertEquals(driver.getTitle(), "selenium/CHANGELOG at master · SeleniumHQ/selenium · GitHub");
		
		emulateChromeNetwork(true);
		driver.get("https://github.com/SeleniumHQ/selenium/blob/master/java/CHANGELOG");
		
		try
		{
			Thread.sleep(3000);
		}catch(Exception e)
		{
			
		}
		
		assertEquals(driver.getTitle(), "github.com");
		
		driver.close();
	}
	
	
	public void emulateChromeNetwork(boolean offline)
	{
		devtool.send(Network.enable(Optional.of(100000000), Optional.empty(), Optional.empty()));
		devtool.send(Network.emulateNetworkConditions(offline, 0, 0, 0, Optional.of(ConnectionType.CELLULAR4G)));
	}
}
