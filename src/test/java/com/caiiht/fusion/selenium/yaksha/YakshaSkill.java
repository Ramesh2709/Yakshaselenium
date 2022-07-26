package com.caiiht.fusion.selenium.yaksha;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.caiiht.fusion.selenium.constants.LoginConstants;
import com.caiiht.fusion.selenium.constants.ManagetagsConstants;
import com.caiiht.fusion.selenium.controls.BaseSetup;
import com.caiiht.fusion.selenium.controls.ControlSelectors;

import au.com.bytecode.opencsv.CSVReader;

public class YakshaSkill {
	static ControlSelectors controls = new ControlSelectors();
	BaseSetup basetSetup = new BaseSetup();
	static LoginConstants logins = new LoginConstants();
	static ManagetagsConstants asses = new ManagetagsConstants();
	private static CSVReader reader = null, reader2 = null;
	private static String fileName = "C:\\Users\\rameshg\\seleneium4Example\\seleniumScriptsRegression\\Input\\Tenant\\Skill.csv";
	private static String fileName1 = "C:\\Users\\rameshg\\seleneium4Example\\seleniumScriptsRegression\\Input\\Tenant\\Category.csv";
	private String[] record, record1, categories;

	@BeforeClass
	public static void setUp() throws FileNotFoundException {
		reader = new CSVReader(new FileReader(fileName), LoginConstants.CSV_FILE_SEPARATOR,
				LoginConstants.DEFAULT_QUOTE_CHARACTER, LoginConstants.CSV_SKIP_HEADER_LINE);
		reader2 = new CSVReader(new FileReader(fileName1), LoginConstants.CSV_FILE_SEPARATOR,
				LoginConstants.DEFAULT_QUOTE_CHARACTER, LoginConstants.CSV_SKIP_HEADER_LINE);
	}

	@Test(priority = 1)
	public void YSkillCreate() throws InterruptedException, IOException {
		Thread.sleep(2000);
		while ((record = reader.readNext()) != null) {
			BaseSetup.driver.navigate().to("https://yaksha-staging-ui.azurewebsites.net/default/app/manage-tags");
			controls.clickByXpath(ManagetagsConstants.createskill);
			controls.sendTextByXpath(ManagetagsConstants.skillname, record[0]);
			controls.clickByXpath(ManagetagsConstants.savebutton);
			Thread.sleep(2000);
			controls.clickByXpath(ManagetagsConstants.skillstab);
			controls.sendTextByXpath(ManagetagsConstants.skillsearch, record[0] + "\n");
			try {
				Assert.assertEquals(controls.checkelement("//div/small[text()='" + record[0] + "']"), true,
						record[0] + " : Skill Created successfully");
				Reporter.log(record[0] + " : Skill Created successfully");
			} catch (Exception e) {
				Reporter.log(record[0] + " : Skill Not Created");
				Assert.fail(record[0] + " : Skill Not Created");
			}
		}
	}

	@Test(priority = 2)
	public void YCategoryCreate() throws InterruptedException, IOException {
		Thread.sleep(2000);
		while ((record1 = reader2.readNext()) != null) {
			BaseSetup.driver.navigate().to("https://yaksha-staging-ui.azurewebsites.net/default/app/manage-tags");
			controls.clickByXpath(ManagetagsConstants.createcategory);
			controls.sendTextByXpath(ManagetagsConstants.categoryname, record1[0]);
			controls.clickByXpath(ManagetagsConstants.catskilldropdown);
			String str = record1[1];
			categories = str.split(",");
			for (String a : categories) {
				controls.sendTextByXpath(ManagetagsConstants.skillselectsearch, a);
				Thread.sleep(1000);
				controls.clickByXpath("//input[@aria-label='" + a + "']/..");
				controls.cleartextbyxpath(ManagetagsConstants.skillselectsearch);
			}
			controls.clickByXpath(ManagetagsConstants.categoryname);
			controls.clickByXpath(ManagetagsConstants.savebutton2);
			try {
				Assert.assertEquals(controls.checkelement("//span/mat-panel-title[text()=' " + record1[0] + " ']"),
						true, "Category created");
				Reporter.log(record1[0] + ": Category created with " + controls.gettextbyxpath(
						"//span/mat-panel-title[text()=' " + record1[0] + " ']/..//mat-panel-description/div/small"));
			} catch (Exception e) {
				Reporter.log("Category not created properly");
				Assert.fail("Category not created properly");
			}
		}
	}
}
