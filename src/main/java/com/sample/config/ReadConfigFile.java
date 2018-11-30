package com.sample.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfigFile {

	public Properties prop;
	public FileInputStream fis;
	public String geckodriverPath;
	public String chromedriverPath;
	public String drivername;

	public String getDriverPath(String drivername) {
		getProperties();
		this.drivername = drivername;
		if (drivername.equals("geckodriver")) {
			geckodriverPath = prop.getProperty("geckodriver");
			System.out.println(geckodriverPath);
			return geckodriverPath;
		} else if (drivername.equals("chromedriver")) {
			chromedriverPath = prop.getProperty("chromedriver");
		}
		System.out.println(chromedriverPath);
		return chromedriverPath;
	}

	public void getProperties() {

		try {
			final File configFileFromResources = new File("src/main/resources/config/config.properties");
			final String path = configFileFromResources.getAbsolutePath();
			fis = new FileInputStream(path);
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prop = new Properties();
		try {
			prop.load(fis);
			fis.close();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getUrl() {
		getProperties();
		final String url = prop.getProperty("url");
		return url;

	}

}
