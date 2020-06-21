package com.test.Selenium4ChromeDevTools;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.emulation.Emulation;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selenium.library.SeleniumUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EmulateGeoLocation {

	WebDriver driver;
	DevTools devTool;
	SeleniumUtility utility=new SeleniumUtility();
	
	@BeforeClass
	public void setDriver()
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		devTool=((ChromeDriver)driver).getDevTools();
		devTool.createSession();
	}
	
	
	
	/**
	 * @implNote In this test case, we are emulating the geo-location to "New York" city
	 */
	@Test
	public void verifyGeoLocationTest1()
	{
		devTool.send(Emulation.setGeolocationOverride(Optional.of(40.712776), Optional.of(-74.005974), Optional.of(100)));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://online.citi.com/US/ag/citibank-location-finder");
		By txtBoxLocation=By.cssSelector("input[placeholder='Enter a Location']");
		utility.waitTillElementIsPresent(driver,Duration.ofSeconds(30), txtBoxLocation);
		utility.waitTillElementIsClickable(driver, Duration.ofSeconds(30), txtBoxLocation);
		String locationName = driver.findElement(txtBoxLocation).getAttribute("value");
		Assert.assertTrue(locationName.contains("New York"), "emulate geo-location city should be 'New York'");
	}
	
	
	/**
	 * @implNote In this test case, we are emulating the geo-location to "New York" city
	 */
	@Test
	public void verifyGeoLocationTest2()
	{
		devTool.send(Emulation.setGeolocationOverride(Optional.of(40.712776), Optional.of(-74.005974), Optional.of(100)));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://locators.bankofamerica.com/");
		utility.waitTillElementIsPresent(driver,Duration.ofSeconds(30), By.id("aria-map-list-header"));
	}
	
	
	
	/**
	 * @implNote In this test case, we are emulating the geo-location to "New York" city
	 */
	@Test
	public void verifyGeoLocationTest3()
	{
		devTool.send(Emulation.setGeolocationOverride(Optional.of(40.712776), Optional.of(-74.005974), Optional.of(100)));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.microsoft.com/en-us/about/officelocator?");
		By locateBtn=By.id("LocateMeButton");
		utility.waitTillElementIsClickable(driver, Duration.ofSeconds(30), locateBtn);
		driver.findElement(locateBtn).click();
		utility.waitTillElementIsPresent(driver,Duration.ofSeconds(30), locateBtn);
	}
	
	
	/**
	 * @implNote In this test case, we are emulating the geo-location to "New York" city
	 * @implNote Emulating is not working with this web portal (google map)
	 */
	@Test
	public void verifyGeoLocationTest4()
	{
		devTool.send(Emulation.setGeolocationOverride(Optional.of(40.712776), Optional.of(-74.005974), Optional.of(100)));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.google.com/maps/");
	}
	
	
	/**
	 * @implNote In this test case, we are emulating the geo-location to "New York" city
	 * @implNote Emulating is not working with this web portal (mylocation.org)
	 */
	@Test
	public void verifyGeoLocationTest5()
	{
		devTool.send(Emulation.setGeolocationOverride(Optional.of(40.712776), Optional.of(-74.005974), Optional.of(100)));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);		
		driver.get("https://mylocation.org/");
	}
	
	
	@AfterClass
	public void tearDown()
	{
		driver.close();
	}
}
