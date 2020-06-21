package com.test.Selenium4ChromeDevTools;

import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.network.Network;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InterceptNetwork {

	@Test
	public void verifyInterceptNetwork()
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		
		DevTools devTool = driver.getDevTools();
		devTool.createSession();
		
		devTool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		
		devTool.send(Network.setBlockedURLs(ImmutableList.of("*.jpg", "*.jpeg", "*.png","*.css")));
		
		driver.manage().window().maximize();
		
		driver.get("https://www.amazon.in/b?node=1375424031");
	}
}
