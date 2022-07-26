package com.caiiht.fusion.selenium.yaksha;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.caiiht.fusion.selenium.constants.LoginConstants;
import com.caiiht.fusion.selenium.controls.BaseSetup;
import com.caiiht.fusion.selenium.controls.ControlSelectors;

//import com.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVReader;

public class YakshaUserimport {
	static ControlSelectors controls = new ControlSelectors();
	BaseSetup basetSetup = new BaseSetup();
	static LoginConstants logins = new LoginConstants();
	private static CSVReader reader = null;
	private static String fileName = "C:\\Users\\rameshg\\seleneium4Example\\seleniumScriptsRegression\\Input\\Tenant\\Createtenant.csv";
	private String[] record;

	@BeforeClass
	public static void setUp() throws FileNotFoundException {
		reader = new CSVReader(new FileReader(fileName), LoginConstants.CSV_FILE_SEPARATOR,
				LoginConstants.DEFAULT_QUOTE_CHARACTER, LoginConstants.CSV_SKIP_HEADER_LINE);
	}

	@Test
	public void YUserImport() throws InterruptedException, IOException, AWTException {
		Thread.sleep(2000);
		basetSetup.openUserUrl();
		controls.clickByXpath(LoginConstants.importuser);
		Thread.sleep(5000);
		controls.clickByXpath(LoginConstants.fileup);
		Robot rb = new Robot();
		Thread.sleep(10000);
		StringSelection str = new StringSelection("C:\\Users\\rameshg\\Downloads\\Users_Bulk_Upload.xlsx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		// press Contol+V for pasting
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		// release Contol+V for pasting
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		// for pressing and releasing Enter
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(10000);

		controls.clickByXpath(LoginConstants.upload);
		try {
			Thread.sleep(2000);
			Reporter.log(controls.gettextxpath(LoginConstants.successmessage));
			Assert.assertEquals(controls.checkelement(LoginConstants.successmessage), true, "File uploaded");
			// Reporter.log("File uploaded successfully");
		} catch (Exception e) {
			Reporter.log("File not uploaded");
			Assert.fail("File not uploaded");
		}

	}
}
