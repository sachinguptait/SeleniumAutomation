package com.test.Selenium4ChromeDevTools;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.security.Security;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OpenInsecureWebsite {
	
	WebDriver driver;

	@BeforeTest
	public void setDriver()
	{
		WebDriverManager.chromedriver().setup();		
		WebDriverManager.chromedriver().version("83.0").arch64().setup();
		driver=new ChromeDriver();
	}
	
	
	@Test
	public void openInsecureWebPortal()
	{
		DevTools devtool= ((ChromeDriver)driver).getDevTools();
		devtool.createSession();
		devtool.send(Security.enable());
		devtool.send(Security.setIgnoreCertificateErrors(true));
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://untrusted-root.badssl.com/");
	}
}
