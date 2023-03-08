package com.CMS.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigParser {

	public static FileInputStream fis;
	public static Properties prop;

	public ConfigParser(String filepath) throws IOException {
		fis = new FileInputStream(filepath);
		prop = new Properties();
		prop.load(fis);

	}

	public String getPropertiesValue(String attribute) {
		return prop.getProperty(attribute);
	}

}
