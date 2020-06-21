package com.test.Selenium4BasicFeatures;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Selenium4Features {

	WebDriver driver;
	
	@BeforeClass
	public void setUp()
	{
		WebDriverManager.chromedriver().setup();
		WebDriverManager.chromedriver().version("83.0").arch64().setup();
		driver=new ChromeDriver();
	
	}
	
	@Test
	public void selenium4Features() throws IOException
	{
		driver.get("https://www.irctc.co.in/");
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		WebElement logoIRCTC = driver.findElement(By.cssSelector("img[alt='Indian railway logo']"));
		
		File src = logoIRCTC.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(src, new File("./ScreenShot/logoIRCTC.png"));
		
		WebElement fromTxtBox = driver.findElement(By.cssSelector("input[placeholder='From*']"));
		Rectangle rect = fromTxtBox.getRect();
		
		System.out.println(rect.getHeight());
		System.out.println(rect.getWidth());
		System.out.println(rect.getX());
		System.out.println(rect.getY());
		
		
		driver.manage().window().fullscreen();
		
		driver.manage().window().minimize();
	}
}
