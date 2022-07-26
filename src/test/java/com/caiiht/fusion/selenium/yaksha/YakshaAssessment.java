package com.caiiht.fusion.selenium.yaksha;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.caiiht.fusion.selenium.constants.LoginConstants;
import com.caiiht.fusion.selenium.constants.YakshaAssessmentconst;
import com.caiiht.fusion.selenium.controls.BaseSetup;
import com.caiiht.fusion.selenium.controls.ControlSelectors;

import au.com.bytecode.opencsv.CSVReader;

public class YakshaAssessment {
	static ControlSelectors controls = new ControlSelectors();
	BaseSetup basetSetup = new BaseSetup();
	static LoginConstants logins = new LoginConstants();
	static YakshaAssessmentconst asses = new YakshaAssessmentconst();
	private static CSVReader reader = null;
	private static String fileName = "C:\\Users\\rameshg\\seleneium4Example\\seleniumScriptsRegression\\Input\\Tenant\\Assementcreate.csv";
	private String[] record, categories;

	@BeforeClass
	public static void setUp() throws FileNotFoundException {
		reader = new CSVReader(new FileReader(fileName), LoginConstants.CSV_FILE_SEPARATOR,
				LoginConstants.DEFAULT_QUOTE_CHARACTER, LoginConstants.CSV_SKIP_HEADER_LINE);
	}

	@Test
	public void YAssessmentcreate() throws InterruptedException, IOException {
		Thread.sleep(2000);
		while ((record = reader.readNext()) != null) {
			BaseSetup.driver.navigate()
					.to("https://yaksha-staging-ui.azurewebsites.net/default/app/assessment/create-assessment");
			controls.clickByXpath(YakshaAssessmentconst.selectcategory);
			String str = record[0];
			categories = str.split(",");
			for (String a : categories) {
				controls.sendTextByXpath(YakshaAssessmentconst.categorysearch, a);
				Thread.sleep(3000);
				controls.clickByXpath("(//li/input[@aria-label='" + a + "']/..)[1]");
				controls.cleartextbyxpath(YakshaAssessmentconst.categorysearch);
			}
			controls.clickByXpath(YakshaAssessmentconst.Assessmenttitle);
			controls.sendTextByXpath(YakshaAssessmentconst.Assessmenttitle, record[1]);
			controls.sendTextByXpath(YakshaAssessmentconst.Assessmentdesc, record[2]);
			controls.sendTextByXpath(YakshaAssessmentconst.AssessmentInstruction, record[3]);
			controls.clickByXpath(YakshaAssessmentconst.savebutton);
			Thread.sleep(4000);
			try {
				boolean c1=controls.checkelement("//h3[text()=' "+record[1]+"']");
				boolean c2= controls.checkelement(YakshaAssessmentconst.draftcheck);
				if(c1==true && c2==true) {
					Assert.assertEquals(true, true,"Assesment created as draft");
					Reporter.log(record[1] +":Assesment created as draft");
				}
				else {
					Reporter.log(record[1] +":Assesment not created as draft");
					Assert.fail("Assesment not created as draft");
				}
					
			}
			catch(Exception e) {
				Reporter.log(record[1] +":Assesment not created as draft");
				Assert.fail("Assesment not created as draft");
			}
		}
	}
}
