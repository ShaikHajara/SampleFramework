package com.sample.basepage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {

	public WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public void assertEquals(WebElement ele, String expected) {
		try {
			final String actual = ele.getText();
			Assert.assertEquals(actual, expected, "Both strings are not equal.Actual string is:" + actual);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public void assertTrue(String actual, String expected) {
		try {
			Assert.assertTrue(compare(actual, expected), "Both strings are not equal");
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public void clear(WebElement ele, String value) {
		waitForVisibilityOfElement(ele, 60);
		ele.clear();
	}

	public void click(WebElement ele, String value) {
		waitForVisibilityOfElement(ele, 60);
		ele.click();
	}

	public void clickUsingActions(WebElement ele, int timeOutInSecs) {
		final Actions action = new Actions(driver);
		action.click(ele).build().perform();
	}

	public boolean compare(String actual, String expected) {
		return actual.equalsIgnoreCase(expected);
	}

	public void doubleClick(WebElement ele, int timeOutInSecs) {
		final Actions action = new Actions(driver);
		action.doubleClick(ele).build().perform();
	}

	public void drapAndDrop(WebElement sourceEle, WebElement destEle, int timeOutInSecs) {
		final Actions action = new Actions(driver);
		action.dragAndDrop(sourceEle, destEle).build().perform();
	}

	public void getAllElementsTextFromDropdown(WebElement ele) {
		final Select s = new Select(ele);
		s.getOptions().stream().toArray();
		s.getOptions().forEach(li -> System.out.println(li.getText()));
	}

	public int getColumnsOfWebTable(WebElement ele) {
		List<WebElement> column;
		int columnCount = 0;
		final List<WebElement> row = ele.findElements(By.tagName("tr"));
		for (int i = 1; i < row.size(); i++) {
			column = row.get(i).findElements(By.tagName("td"));
			columnCount = column.size();
		}
		return columnCount;
	}

	public int getRowsOfWebTable(WebElement ele) {
		final List<WebElement> row = ele.findElements(By.tagName("tr"));
		return row.size();
	}

	public long getTotalCountFromDropdown(WebElement ele) {
		final Select s = new Select(ele);
		final long count = s.getOptions().stream().count();
		return count;
	}

	public boolean isElementDisplayed(WebElement ele) {
		final boolean result = ele.isDisplayed();
		return result;
	}

	public boolean isElementSelected(WebElement ele) {
		return ele.isSelected();
	}

	public void navigateToURL(String URL) {
		driver.get(URL);
	}

	public void sendKeys(WebElement ele, String value) {
		waitForVisibilityOfElement(ele, 60);
		ele.sendKeys(value);
	}

	public void takeScreenshotAtEndOfTest() throws IOException {
		final File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		final String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

	public void waitForAlertIsPresent(int timeOutInSecs) {
		final WebDriverWait wait = new WebDriverWait(driver, timeOutInSecs);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitForElementToBeClickable(By by, int timeInSecs) {
		final WebDriverWait wait = new WebDriverWait(driver, timeInSecs);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public void waitForElementToBeClickable(WebElement ele, int timeInSecs) {
		final WebDriverWait wait = new WebDriverWait(driver, timeInSecs);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void waitForVisibilityOfElement(WebElement ele, int timeInSecs) {
		final WebDriverWait wait = new WebDriverWait(driver, timeInSecs);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitForVisibilityOfText(By by, String value, int timeInSecs) {
		final WebDriverWait wait = new WebDriverWait(driver, timeInSecs);
		wait.until(ExpectedConditions.textToBe(by, value));
	}

}
