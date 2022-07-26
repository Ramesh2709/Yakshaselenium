package com.caiiht.fusion.selenium.controls;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.caiiht.fusion.selenium.constants.DriverConstants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseSetup {
	private static final Logger log = LogManager.getLogger(ControlSelectors.class.getName());

	/*
	 * public static AppiumDriverLocalService service; public static
	 * AppiumServiceBuilder builder; public static ServerArgument argument;
	 */
	public static ControlSelectors controls = new ControlSelectors();
	public static String baseURL = null;

	public static String unifiedURL = null;

	public static String unifiedHomePageURL = null;

	public static String sysadminURL = null;

	public static String browser = null;

	public static String nodeUrl = null;

	public static String nodeUrlPort = null;

	public static String fusionUserName = null;

	public static String fusionUserPassword = null;

	public static String authenticationType = null;

	public static String executeInRemoteMachine = null;

	public static String mobileDevice = null;

	public static String mobileBrowserName = null;

	public static String mobileVersion = null;

	public static String mobilePlatform = null;

	public static String mobilePlatformName = null;

	public static String mobileDeviceName = null;

	public static String mobileStartupTimeOut = null;

	public static String[] executionMode = null;

	public static String currentExecutionMode = null;

	public static String inputFilePath = null;

	public static String zetaEnabled = null;

	public static String chromeDriverPath = null;

	public static String ieDriverPath = null;

	public static String userFirstName = null;

	public static String userLastName = null;

	public static String custodianEmail = null;

	public static String custodianEmailPassword = null;

	public static String scenariosFile = null;

	public static WebDriver driver;

	// public static AndroidDriver<TouchableElement> androidDriver;

	public static String savepath = null;

	public static DesiredCapabilities desCapabilities;// = new DesiredCapabilities();

	public static PropertiesManipulation properties = new PropertiesManipulation();

	public static String epmInputFilePath = null;

	public static String screenshotFolder;

	public static String xmlFilesFolder = null;

	public static String autoItLocation = null;

	public static String emailSubject = null;

	static {
		try {
			browser = properties.getPropertyByKey("BROWSER");
			baseURL = properties.getPropertyByKey("BASE_URL");
			fusionUserName = properties.getPropertyByKey("USER_NAME");
			fusionUserPassword = properties.getPropertyByKey("PASSWORD");
			executionMode = properties.getPropertyByKey("EXECUTION_MODE").trim().split("\\s*,\\s*");
			/*
			 * inputFilePath = properties.getPropertyByKey("INPUT_FILE_PATH");
			 * authenticationType = properties.getPropertyByKey("AUTHENTICATION_TYPE");
			 * executeInRemoteMachine = properties.getPropertyByKey("REMOTE_MACHINE");
			 * nodeUrl = properties.getPropertyByKey("NODE_URL"); nodeUrlPort =
			 * properties.getPropertyByKey("NODE_URL_PORT"); fusionUserName =
			 * properties.getPropertyByKey("USER_NAME"); fusionUserPassword =
			 * properties.getPropertyByKey("PASSWORD"); executionMode =
			 * properties.getPropertyByKey("EXECUTION_MODE").trim().split("\\s*,\\s*");
			 * zetaEnabled = properties.getPropertyByKey("EDDM_ENABLED"); chromeDriverPath =
			 * properties.getPropertyByKey("CHROME_DRIVER_PATH"); ieDriverPath =
			 * properties.getPropertyByKey("IE_DRIVER_PATH"); mobileDevice =
			 * properties.getPropertyByKey("DEVICE"); mobileBrowserName =
			 * properties.getPropertyByKey("BROWSER_NAME"); mobileVersion =
			 * properties.getPropertyByKey("VERSION"); mobilePlatform =
			 * properties.getPropertyByKey("PLATFORM"); mobilePlatformName =
			 * properties.getPropertyByKey("PLATFORM_NAME"); mobileDeviceName =
			 * properties.getPropertyByKey("DEVICE_NAME"); mobileStartupTimeOut =
			 * properties.getPropertyByKey("TIME_OUT"); userFirstName =
			 * properties.getPropertyByKey("FIRST_NAME"); userLastName =
			 * properties.getPropertyByKey("LAST_NAME"); custodianEmail =
			 * properties.getPropertyByKey("CUSTODIAN_EMAIL"); custodianEmailPassword =
			 * properties.getPropertyByKey("CUSTODIAN_PWD"); savepath =
			 * properties.getPropertyByKey("savepath"); scenariosFile =
			 * properties.getPropertyByKey("SCENARIOS_FILE"); screenshotFolder =
			 * properties.getPropertyByKey("FOLDER_STRUCTURE"); xmlFilesFolder =
			 * properties.getPropertyByKey("XML_FILES"); autoItLocation =
			 * properties.getPropertyByKey("AutoIt_PATH"); emailSubject =
			 * properties.getPropertyByKey("LH_LINKS_EMAIL_SUBJECT");
			 */
			Thread.sleep(8000);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Test
	public static void launchBrowser() throws Exception {
		if (executionMode[0].equalsIgnoreCase("browser")) {
			currentExecutionMode = "browser";
			desCapabilities = new DesiredCapabilities();

			if (browser.equalsIgnoreCase("Edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} else if (browser.equalsIgnoreCase("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			driver.manage().window().maximize();

		}
	}

	public String getSysadminURL() {
		if (sysadminURL == null) {
			sysadminURL = baseURL + "/fusion/ui/pages/adminLogin.htm";
		}
		return sysadminURL;
	}

	public String getUnifiedURL() {
		if (unifiedURL == null) {
			unifiedURL = baseURL + "/default/account/landing";
		}
		return unifiedURL;
	}

	public void openTenantUrl() {
		driver.navigate().to(baseURL + "/default/app/tenants");
	}
	public void openUserUrl() {
		driver.navigate().to(baseURL + "/default/app/users");
	}

	public String getUnifiedHomePageURL() {
		if (unifiedHomePageURL == null) {
			unifiedHomePageURL = baseURL + "/fusion/ui/pages/start.htm";
		}
		return unifiedHomePageURL;
	}

	public void openSysadminUrl() {
		driver.get(getSysadminURL());
	}

	public void openUnifiedUrl() throws InterruptedException {
		Thread.sleep(2000);
		driver.get(getUnifiedURL());
	}

	public static String getBrowser() {
		if (browser == null) {
			try {
				browser = properties.getPropertyByKey("BROWSER");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return browser;
	}

}