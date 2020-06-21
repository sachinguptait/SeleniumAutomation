package com.test.Selenium4ChromeDevTools;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.log.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GettingChromeConsoleLogs {

	WebDriver driver;
	boolean flag=false;
	
	@BeforeTest
	public void setDriver()
	{
		WebDriverManager.chromedriver().setup();
		WebDriverManager.chromedriver().version("83.0").arch64().setup();		
		driver = new ChromeDriver();
	}
	
	
	@Test
	public void readConsoleLogs()
	{
		
		//Listening console logs
		DevTools devTool = ((ChromeDriver)driver).getDevTools();
		
		//Create session
		devTool.createSession();
		
		//Enabling the logs
		devTool.send(Log.enable());
		
		List<String> list=new ArrayList<String>();
		
		devTool.addListener(Log.entryAdded(), entry -> list.add(entry.getText()));
		
		driver.get("https://www.thehindu.com/");
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		for(Object log:list)
		{
			System.out.println("Logs are:- "+log);
			
			if(log.toString().contains("elements with non-unique id"))
			{
				flag=true;
				break;
			}
		}
		
		Assert.assertTrue(flag, "Console logs appeared.");
	}
}
