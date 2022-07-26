package com.caiiht.fusion.selenium.yaksha;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.caiiht.fusion.selenium.constants.LoginConstants;
import com.caiiht.fusion.selenium.controls.BaseSetup;
import com.caiiht.fusion.selenium.controls.ControlSelectors;

public class yakshalogin {
	static ControlSelectors controls = new ControlSelectors();
	BaseSetup basetSetup = new BaseSetup();
	LoginConstants logins = new LoginConstants();

	@Test
	public void YLogin() throws Exception {
		Thread.sleep(2000);
		basetSetup.openUnifiedUrl();
		controls.sendTextByXpath(logins.username, BaseSetup.fusionUserName);
		controls.sendTextByXpath(logins.password, BaseSetup.fusionUserPassword);
		controls.clickByXpath(logins.loginbutton);
		try {
			Assert.assertEquals(controls.findpathclickable(logins.home), true, "Login succesfully");
			Reporter.log("Login succesfully- Home page displayed");
		} catch (Exception e) {
			Reporter.log("Home page not loaded at 20 sec");
			Assert.fail("Home page not loaded at 20 sec");
		}

	}

}
