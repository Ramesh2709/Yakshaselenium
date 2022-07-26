package com.caiiht.fusion.selenium.controls;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesManipulation
{

	private Properties properties = new Properties();

	private InputStream input = null;

	private String propertyFileName = "./selenium.properties";

	/**
	 * Method to get the value from property file based on the provided key
	 * 
	 * @param key
	 * @return String
	 * @throws IOException
	 * @author Prakash Sevanan
	 * @since 1.0
	 */
	public String getPropertyByKey(String key) throws IOException {
		input = new FileInputStream(propertyFileName);
		if (input != null) {
			properties.load(input);
		} else {
			throw new FileNotFoundException("Property File " + propertyFileName + "is not available in the specified path");
		}
		return properties.getProperty(key);
	}
}