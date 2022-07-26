package com.caiiht.fusion.selenium.constants;

public class LoginConstants {
	public  String username="//input[@aria-label='Username']";
	public  String password="//input[@aria-label='Password']";
	public  String loginbutton="//button[@aria-label='LOGIN']";
	public  String home="//span[text()='Dashboard']";
	public  String addtenentbutton="//button[text()=' Add Tenant']";
	
	public final static char CSV_FILE_SEPARATOR = '|';
	public final static char DEFAULT_QUOTE_CHARACTER = '"';
	public final static char DOUBLE_QUOTE_CHARACTER = '"';
	public final static int CSV_SKIP_HEADER_LINE = 1;
	
	public static String importuser="//button[text()=' Import Users ']";
	public static String fileup="//div[@class='custom-file']";
	public static String upload="//button[text()='Upload']";
	public static String successmessage="//div[@id=\"toast-container\"]/div";
}
