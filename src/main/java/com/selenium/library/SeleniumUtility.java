package com.selenium.library;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.security.Security;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.collect.ImmutableMap;

public class SeleniumUtility {
	private static DevTools devTools;
	private static WebDriver driver;
	private static ChromeDriver chromeDriver;
	
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
	
	public void createDevToolsSession(ChromeDriver driver) {
		devTools.createSession();
	}
	
	public void openInsecureWebsite(ChromeDriver driver, String url) {
		devTools = driver.getDevTools();
		createDevToolsSession(driver);
		devTools.send(Security.enable());
		devTools.send(Security.setIgnoreCertificateErrors(true));
		driver.get(url);
	}
	
	
	public void updateNetworkForChromeBrowser(ChromeDriver driver, String url, boolean offline,
			int latency, int download_throughput, int upload_throughput) {
		// Set the conditions
		Map<String, Comparable> map = new HashMap<String, Comparable>();
		map.put("offline", offline);
		map.put("latency", latency);
		map.put("download_throughput", download_throughput);
		map.put("upload_throughput", upload_throughput);
		CommandExecutor commandExecutor = driver.getCommandExecutor();
		try {
			commandExecutor.execute(new Command(driver.getSessionId(), "setNetworkConditions",
					ImmutableMap.of("network_conditions", ImmutableMap.copyOf(map))));
			driver.get(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public void waitTillElementIsPresent(WebDriver driver,Duration timeout, By locator)
	{
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
    
	
	public void waitTillElementIsClickable(WebDriver driver,Duration timeout, By locator)
	{
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
}
