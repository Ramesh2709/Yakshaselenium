package com.caiiht.fusion.selenium.yaksha;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.caiiht.fusion.selenium.constants.LoginConstants;
import com.caiiht.fusion.selenium.constants.QuestionsConstants;
import com.caiiht.fusion.selenium.constants.YakshaAssessmentconst;
import com.caiiht.fusion.selenium.controls.BaseSetup;
import com.caiiht.fusion.selenium.controls.ControlSelectors;

import au.com.bytecode.opencsv.CSVReader;

public class YakshaQuestionCreate {
	static ControlSelectors controls = new ControlSelectors();
	BaseSetup basetSetup = new BaseSetup();
	static LoginConstants logins = new LoginConstants();
	static QuestionsConstants asses = new QuestionsConstants();
	private static CSVReader reader = null;
	private static String fileName = "C:\\Users\\rameshg\\seleneium4Example\\seleniumScriptsRegression\\Input\\Tenant\\Question.csv";
	private String[] record, categories;

	@BeforeClass
	public static void setUp() throws FileNotFoundException {
		reader = new CSVReader(new FileReader(fileName), LoginConstants.CSV_FILE_SEPARATOR,
				LoginConstants.DEFAULT_QUOTE_CHARACTER, LoginConstants.CSV_SKIP_HEADER_LINE);
	}

	@Test
	public void YQuestioncreate() throws InterruptedException, IOException {
		Thread.sleep(2000);
		BaseSetup.driver.navigate()
				.to("https://yaksha-staging-ui.azurewebsites.net/default/app/question/create-question");
		while ((record = reader.readNext()) != null) {
			controls.clickByXpath(QuestionsConstants.questiontypeselect);
			controls.clickByXpath(
					QuestionsConstants.questiontypeselect + "/option[contains(text(),'" + record[0] + "')]");
			controls.clickByXpath(QuestionsConstants.proficiency);
			controls.clickByXpath(QuestionsConstants.proficiency + "/option[contains(text(),'" + record[1] + "')]");
			controls.clickByXpath(QuestionsConstants.category);
			controls.sendTextByXpath(QuestionsConstants.categorysearch, record[2]);
			controls.clickByXpath("//input[@aria-label='" + record[2] + "']/..");
			controls.clickByXpath(QuestionsConstants.skill);
			controls.sendTextByXpath(QuestionsConstants.skillsearch, record[3]);
			controls.clickByXpath("//input[@aria-label='" + record[3] + "']/..");
			controls.clickByXpath(QuestionsConstants.subskill);
			controls.sendTextByXpath(QuestionsConstants.subskill, record[4] + "\n");
			controls.sendTextByXpath(QuestionsConstants.question, record[5]);
			if (record[0].equalsIgnoreCase("MultipleChoice")) {
				controls.sendTextByXpath("(" + QuestionsConstants.options + ")[1]", record[6]);
				controls.sendTextByXpath("(" + QuestionsConstants.options + ")[2]", record[7]);
				controls.sendTextByXpath("(" + QuestionsConstants.options + ")[3]", record[8]);
				controls.sendTextByXpath("(" + QuestionsConstants.options + ")[4]", record[9]);
				controls.sendTextByXpath("(" + QuestionsConstants.options + ")[5]", record[10]);
				controls.sendTextByXpath("(" + QuestionsConstants.options + ")[6]", record[11]);
				String str = record[12];
				categories = str.split(",");
				int i = categories.length;
				for (int j = 0; j < i; j++) {
					controls.clickByXpath("(" + QuestionsConstants.options + "/../div)[" + categories[j] + "]");
					Thread.sleep(1000);
				}
				controls.sendTextByXpath(QuestionsConstants.hints, record[13]);
				controls.clickByXpath(QuestionsConstants.savebutton);
				try {
					Thread.sleep(2000);
					Assert.assertEquals(controls.checkelement(LoginConstants.successmessage), true,
							"Question created successfully");
					Reporter.log(controls.gettextxpath(LoginConstants.successmessage));
				} catch (Exception e) {
					Reporter.log("Question not created");
					Assert.fail("Question not created");
				}
			}
		}

	}
}
