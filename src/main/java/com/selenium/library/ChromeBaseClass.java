package com.selenium.library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeBaseClass {
	
	public WebDriver driver;
	public int lessWait=10;
	public int mediumWait=20;
	public int highWait=60;
	
	
	public void driverSetUP()
	{
		WebDriverManager.chromedriver().setup();
		WebDriverManager.chromedriver().version("83.0").arch64().setup();
		driver = new ChromeDriver();
	}

}
