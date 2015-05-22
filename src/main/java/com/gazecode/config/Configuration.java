package com.gazecode.config;

import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	private Properties properties = new Properties();

	public Configuration() {
		try {
			
			properties.load(Configuration.class.getResourceAsStream("/location.properties"));
			
		} catch (Exception e) {
			System.out.println("Error while loading");
			e.printStackTrace();
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}
	
	//for unit testing
	public static void main(String[] args) {
		System.out.println("Starting the process");
		Configuration config = new Configuration();
		System.out.println(config.getProperty("INDEX_DIR"));
		System.out.println(config.getProperty("TEST"));
	}
}
