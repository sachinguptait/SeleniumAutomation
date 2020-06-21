package com.selenium.library;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeBaseClass {
	
	public ChromeDriver driver;
	public int lessWait=10;
	public int mediumWait=20;
	public int highWait=60;
	
	
	@BeforeClass
	public void driverSetUP()
	{
		WebDriverManager.chromedriver().setup();
		WebDriverManager.chromedriver().version("83.0").arch64().setup();
		driver = new ChromeDriver();
	}

}
