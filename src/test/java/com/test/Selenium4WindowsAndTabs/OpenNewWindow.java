package com.test.Selenium4WindowsAndTabs;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.report.utility.ExtentTestManager;
import com.selenium.library.SeleniumMethods;

public class OpenNewWindow {

	WebDriver driver;
	SeleniumMethods seleniumMethods = new SeleniumMethods();
	String domainName = "newtours.demoaut.com";
	String url1 = "https://google.in";
	String url2 = "http://newtours.demoaut.com/";
	String url3 = "http://newtours.demoaut.com/mercuryregister.php";
	boolean flag = false;

	@Test
	public void verifyTestScenarioInNewWindow() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		try {
			driver.get(url1);
			WebElement googleSearchTextBox = driver.findElement(By.xpath("//input[@type='text'  and @name='q']"));
			googleSearchTextBox.sendKeys("newtours.demoaut");
			googleSearchTextBox.sendKeys(Keys.ENTER);

			List<WebElement> searchElements = driver.findElements(By.cssSelector(".r div>cite"));

			for (WebElement ele : searchElements) {
				System.out.println(ele.getText().trim());
				if (ele.getText().trim().equalsIgnoreCase(domainName)) {
					flag = true;
					ExtentTestManager.getTest().log(Status.INFO,
							"Domain name '" + domainName + "'  has found in google search result.");
					Assert.assertTrue(true);
					break;
				}
			}

			if (flag) {
				// open new Window and login
				seleniumMethods.openNewWindow(driver, url2);
				Thread.sleep(2000);
				driver.findElement(RelativeLocator.withTagName("input").above(By.name("password"))
						.below(By.xpath("//td//b[text()='sign-in here']"))).sendKeys("LearnAutomation");
				driver.findElement(By.name("password")).sendKeys("LearnAutomation@#");
				driver.findElement(RelativeLocator.withTagName("input").below(By.name("password"))).click();
				Assert.assertTrue(driver.findElement(By.xpath("//b[text()='Passengers:']")).isDisplayed());
				ExtentTestManager.getTest().log(Status.INFO, "User has logged in successfully.");
				Thread.sleep(3000);

				// open registration page in new Window and verify the login session
				seleniumMethods.openNewWindow(driver, url3);
				Thread.sleep(3000);
				boolean signoff = driver.findElement(By.linkText("SIGN-OFF")).isDisplayed();
				System.out.println("signoff -" + signoff);
				Assert.assertTrue(signoff);
				ExtentTestManager.getTest().log(Status.INFO,
						"Login Session is still activated after opening registration page in new Window");
				Thread.sleep(3000);

				// switch to first Window and refresh the page
				seleniumMethods.switchToRespectiveWindow(driver, 0);
				ExtentTestManager.getTest().log(Status.INFO, "Switching to 1st Window");
				Thread.sleep(3000);
				driver.navigate().refresh();
				ExtentTestManager.getTest().log(Status.INFO, "Refreshing page of 1st Window");
				Thread.sleep(3000);

				// switch to 3rd Window and close the page
				seleniumMethods.switchToRespectiveWindow(driver, 2);
				ExtentTestManager.getTest().log(Status.INFO, "Switching to 3rd Window");
				Thread.sleep(3000);
				seleniumMethods.closeRespectiveWindow(driver, 2);
				ExtentTestManager.getTest().log(Status.INFO, "Closing 3rd Window");
				Thread.sleep(3000);

				// switch to 2nd Window and close the page
				seleniumMethods.switchToRespectiveWindow(driver, 1);
				ExtentTestManager.getTest().log(Status.INFO, "Switching to 2nd Window");
				Thread.sleep(3000);
				seleniumMethods.closeRespectiveWindow(driver, 1);
				ExtentTestManager.getTest().log(Status.INFO, "Closing 2nd Window");
				Thread.sleep(3000);

				// switch to 1st Window and close the page
				seleniumMethods.switchToRespectiveWindow(driver, 0);
				ExtentTestManager.getTest().log(Status.INFO, "Switching to 1st Window");
				Thread.sleep(3000);
				seleniumMethods.closeRespectiveWindow(driver, 0);
				ExtentTestManager.getTest().log(Status.INFO, "Closing 1st Window");
				Thread.sleep(3000);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
