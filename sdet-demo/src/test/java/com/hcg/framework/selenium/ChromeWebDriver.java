package com.hcg.framework.selenium;

import java.io.File;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeWebDriver extends ChromeDriver 
{
	public static final String DEFAULT_DRIVER_SYSTEM_PROPERTY  = "webdriver.chrome.driver";
	public static final String DEFAULT_DRIVER_EXE_PATH         = "webdrivers" + File.separator + "chromedriver.exe";
	
	static {
		System.setProperty(DEFAULT_DRIVER_SYSTEM_PROPERTY, DEFAULT_DRIVER_EXE_PATH);
	}
	
	public ChromeWebDriver()
	{
		this(DEFAULT_DRIVER_EXE_PATH, null, null, null);
	}

	public ChromeWebDriver(int width, int height)
	{
		this(DEFAULT_DRIVER_EXE_PATH, null, new Dimension(width, height), null);
	}
	
	public ChromeWebDriver(int width, int height, int x, int y)
	{
		this(DEFAULT_DRIVER_EXE_PATH, null, new Dimension(width, height), new Point(x, y));
	}
	
	public ChromeWebDriver(String argPageUrl)
	{
		this(DEFAULT_DRIVER_EXE_PATH, argPageUrl, null, null);
	}
	
	public ChromeWebDriver(String argPageUrl, int width, int height)
	{
		this(DEFAULT_DRIVER_EXE_PATH, argPageUrl, new Dimension(width, height), null);
	}
	
	public ChromeWebDriver(String argPageUrl, int width, int height, int x, int y)
	{
		this(DEFAULT_DRIVER_EXE_PATH, argPageUrl, new Dimension(width, height), new Point(x, y));
	}
	
	public ChromeWebDriver(String argPageUrl, Dimension dimension)
	{
		this(DEFAULT_DRIVER_EXE_PATH, argPageUrl, dimension, null);
	}
	
	public ChromeWebDriver(String argPageUrl, Point point)
	{
		this(DEFAULT_DRIVER_EXE_PATH, argPageUrl, null, point);
	}
	
	public ChromeWebDriver(String argWebDriverExePath, String argPageUrl)
	{
		this(argWebDriverExePath, argPageUrl, null, null);
	}
	
	public ChromeWebDriver(String argWebDriverExePath, String argPageUrl, int width, int height)
	{
		this(argWebDriverExePath, argPageUrl, new Dimension(width, height), null);
	}
	
	public ChromeWebDriver(String argWebDriverExePath, String argPageUrl, int width, int height, int x, int y)
	{
		this(argWebDriverExePath, argPageUrl, new Dimension(width, height), new Point(x, y));
	}
	
	public ChromeWebDriver(String argWebDriverExePath, String argPageUrl, Dimension dimension)
	{
		this(argWebDriverExePath, argPageUrl, dimension, null);
	}
	
	public ChromeWebDriver(String argWebDriverExePath, String argPageUrl, Point point)
	{
		this(argWebDriverExePath, argPageUrl, null, point);
	}
	
	public ChromeWebDriver(String argWebDriverExePath, String argPageUrl, Dimension dimension, Point point)
	{
		super();

		if (argPageUrl != null)
			super.get(argPageUrl);
		
		if (dimension != null)
			super.manage().window().setPosition(point);
		
		if (point != null)
			super.manage().window().setSize(dimension);
	}
	
}
