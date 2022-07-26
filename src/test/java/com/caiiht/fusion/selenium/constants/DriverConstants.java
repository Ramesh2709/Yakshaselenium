package com.caiiht.fusion.selenium.constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.Platform;

/**
 * 
 * @author Ramesh Ganesan
 * @since 1.0
 */
public class DriverConstants
{

	public static final Platform BROWSER_PLATFORM = Platform.VISTA;

	public static final String FIREFOX_BROWSER = "firefox";

	public static final String IE_BROWSER = "internet explorer";

	public static final String CHROME_BROWSER = "chrome";

	public static final int DEFAULT_WAIT_TIME = 40;
	
	public static final String TIME_STAMP = new SimpleDateFormat("ddMMyyHHmmss").format(Calendar.getInstance().getTime());
	
	public static String getStartTime() {
		return "_" + TIME_STAMP;
	}
}
