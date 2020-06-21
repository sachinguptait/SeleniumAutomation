package com.test.Selenium4ChromeDevTools;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.network.Network;
import org.openqa.selenium.devtools.network.model.ConnectionType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;

public class DifferentNetworkSpeed {
	
	WebDriver driver;
	
	@DataProvider
	public Object [][] networkSpeeds()
	{
		return new Object[][]
				{
					{20000, 10000},
					{30000, 15000},
					{50000, 25000},
					{60000, 30000},
				};
	}
	
	
	@Test(dataProvider = "networkSpeeds")
	public void verifyAppWithDifferentNetworkSpeed(int download, int upload)
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		DevTools devTool = ((ChromeDriver)driver).getDevTools();
		devTool.createSession();
		
		devTool.send(Network.enable(Optional.of(100000000), Optional.empty(), Optional.empty()));
		devTool.send(Network.emulateNetworkConditions(false, 5, download, upload, Optional.of(ConnectionType.CELLULAR4G)));
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get("https://amazon.in");
		driver.close();
			
	}
	

}
