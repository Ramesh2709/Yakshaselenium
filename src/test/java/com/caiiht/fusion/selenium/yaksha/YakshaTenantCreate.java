package com.caiiht.fusion.selenium.yaksha;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.caiiht.fusion.selenium.constants.LoginConstants;
import com.caiiht.fusion.selenium.constants.TenantConstants;
import com.caiiht.fusion.selenium.controls.BaseSetup;
import com.caiiht.fusion.selenium.controls.ControlSelectors;
//import com.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVReader;

public class YakshaTenantCreate {
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
	public void YTenantCreate() throws InterruptedException, IOException {
		Thread.sleep(2000);
		basetSetup.openTenantUrl();
		while ((record = reader.readNext()) != null) {
			controls.clickByXpath(logins.addtenentbutton);
			controls.sendTextByXpath(TenantConstants.createTenantname, record[0]);
			controls.sendTextByXpath(TenantConstants.createdisplayName, record[1]);
			controls.sendTextByXpath(TenantConstants.createCSM, record[2]);
			controls.clickByXpath(TenantConstants.createsave);
			controls.sendTextByXpath(TenantConstants.tenantsearch, record[0] + "\n");
			try {
				Assert.assertEquals(
						controls.checkelement("//ngb-highlight[@ng-reflect-result='" + record[0]
								+ "']/../..//ngb-highlight[@ng-reflect-result='Active']"),
						true, "Tenant created succesfully");
				Reporter.log("Tenant '" + record[0] + "' Created successfully with Active status.");
			} catch (Exception e) {
				Reporter.log("Tenant " + record[0] + " not Created properly");
				Assert.fail("Tenant " + record[0] + " not Created");
			}

		}

	}
}
