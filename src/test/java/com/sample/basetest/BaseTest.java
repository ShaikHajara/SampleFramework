package com.sample.basetest;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

import com.sample.config.ReadConfigFile;

public class BaseTest {

	public WebDriver driver;
	ReadConfigFile readConfigFile;

	public String isPageLoaded() {
		final JavascriptExecutor js = (JavascriptExecutor) driver;
		final String state = js.executeScript("return document.readyState").toString();
		return state;
	}

	@BeforeClass
	public void launchBrowser() {
		setup("Chrome");
		navigateToUrl();

	}

	public void navigateToUrl() {
		driver.get(readConfigFile.getUrl());

	}

	public void setup(String Browser) {

		readConfigFile = new ReadConfigFile();

		switch (Browser) {
		case "Firefox":
			System.setProperty("webdriver.gecko.driver", readConfigFile.getDriverPath("geckodriver"));
			driver = new FirefoxDriver();
			break;
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", readConfigFile.getDriverPath("chromedriver"));
			driver = new ChromeDriver();
			break;
		}
	}

}
