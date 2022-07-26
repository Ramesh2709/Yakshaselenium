package com.caiiht.fusion.selenium.controls;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v99.input.Input;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ControlSelectors extends BaseSetup {
	Input i1 = new Input();
	private WebElement element;
	private static final Logger log = LogManager.getLogger(ControlSelectors.class.getName());

	public void openUrl(String url) {
		driver.get(url);
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getCurrentPageTitle() {
		return driver.getTitle();
	}

	public void terminateDriver() throws Exception {
		try {
			if (driver != null) {
				driver.close();
				driver.quit();
				Thread.sleep(5000);
				try {
					if (BaseSetup.executionMode[1].equalsIgnoreCase("mobile")) {
						BaseSetup.executionMode[0] = "mobile";
						BaseSetup.launchBrowser();
					}
				} catch (Exception e) {
					log.info("Only one type of execution is available...");
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public void sendTextByXpath(String xpaths, String value) throws InterruptedException {
		// waitForElementByxpath(xpaths);
		for (int i = 1; i <= 5; i++) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpaths)));
				driver.findElement(By.xpath(xpaths)).sendKeys(value);
				break;
			} catch (UnhandledAlertException e) {
				// alertBox(true);
				Thread.sleep(500);
			} catch (Exception e) {
				System.out.println("Retrying " + i + "... due to : " + e.getMessage());
				Thread.sleep(1000);
			}
		}
	}
	public void cleartextbyxpath(String xpaths) {
		driver.findElement(By.xpath(xpaths)).clear();
		
	}

	public void clickByXpath(String xpaths) throws InterruptedException {
		for (int i = 1; i <= 5; i++) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpaths)));
				driver.findElement(By.xpath(xpaths)).click();
				break;
			} catch (UnhandledAlertException e) {
				// alertBox(true);
				Thread.sleep(500);
			} catch (Exception e) {
				System.out.println("Retrying " + i + "... due to : " + e.getMessage());
				Thread.sleep(1000);
			}
		}

	}
	public String gettextbyxpath(String xpaths) throws InterruptedException {
		Thread.sleep(2000);
		return driver.findElement(By.xpath(xpaths)).getText();
	}

	public boolean findpathclickable(String xpaths) throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpaths)));
			return true;
		} catch (UnhandledAlertException e) {
			Thread.sleep(500);
			return false;
		}

	}

	public boolean checkelement(String xpaths) throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpaths)));
			return true;
		} catch (UnhandledAlertException e) {
			Thread.sleep(500);
			return false;
		}
	}
	public String gettextxpath(String xpaths) throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpaths)));
			return driver.findElement(By.xpath(xpaths)).getText();
		} catch (UnhandledAlertException e) {
			Thread.sleep(500);
			return driver.findElement(By.xpath(xpaths)).getText();
		}
	}

}
