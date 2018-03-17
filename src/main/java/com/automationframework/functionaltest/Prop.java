package com.automationframework.functionaltest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Prop {

	private Properties prop = new Properties();

	public Prop(){
		loadProperties("environment.properties");
	}

	public Prop(String propertiesFile){
		loadProperties(propertiesFile);
	}

	public Properties loadProperties(String propertFile) {

		String propertyFilesPath = getClass().getClassLoader().getResource(propertFile).getPath();

		FileInputStream input = null;
		try {
			input = new FileInputStream(propertyFilesPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public String get(String key) {
		return prop.getProperty(key).trim();
	}
}
