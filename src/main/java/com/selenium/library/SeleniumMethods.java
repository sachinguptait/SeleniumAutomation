package com.selenium.library;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class SeleniumMethods {
	
	public void openNewTab(WebDriver driver, String url)
	{
		driver.switchTo().newWindow(WindowType.TAB).get(url);
	}
	
	
	public void openNewWindow(WebDriver driver, String url)
	{
		driver.switchTo().newWindow(WindowType.WINDOW).get(url);
	}
	
	public Set<String> getAllWindows(WebDriver driver)
	{
		return driver.getWindowHandles();
	}
	
	public void switchToRespectiveWindow(WebDriver driver, int windowNumber)
	{
		Set<String> allWindows = getAllWindows(driver);
		ArrayList<String> windowHandles = new ArrayList<String>(allWindows);
		driver.switchTo().window(windowHandles.get(windowNumber));
	}
	
	
	public void closeRespectiveWindow(WebDriver driver, int windowNumber)
	{
		Set<String> allWindows = getAllWindows(driver);
		ArrayList<String> windowHandles = new ArrayList<String>(allWindows);
		driver.switchTo().window(windowHandles.get(windowNumber)).close();
	}
}
