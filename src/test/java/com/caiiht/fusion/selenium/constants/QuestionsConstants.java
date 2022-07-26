package com.caiiht.fusion.selenium.constants;

public class QuestionsConstants {
	public static String questiontypeselect="//select[@ng-reflect-name='questionType']";
	public static String proficiency="//select[@ng-reflect-name='proficiency']";
	public static String category="//span[text()='Select Category']/..";
	public static String categorysearch="//ng-multiselect-dropdown[@ng-reflect-placeholder='Select Category']//input[@aria-label='multiselect-search']/..";
	public static String skill="//span[text()='Select Skill']/..";
	public static String skillsearch="//ng-multiselect-dropdown[@ng-reflect-placeholder='Select Skill']//input[@aria-label='multiselect-search']/..";
	public static String subskill="//input[@formcontrolname='subSkill']";
	public static String question="//div[@data-placeholder='Enter question here...']";
	public static String options="//div/input[contains(@placeholder,'Option')]";
	public static String hints="//div[@data-placeholder='Enter hint here...']";
	public static String savebutton="//button[contains(text(),'Save')]";
}
