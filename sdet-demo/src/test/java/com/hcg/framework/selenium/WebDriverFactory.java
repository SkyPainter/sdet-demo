package com.hcg.framework.selenium;

import java.io.File;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory 
{
	// Chorme webdriver system property and exe path
	public static String CHROME_WEB_DRIVER_SYTEM_PROPERTY   = "webdriver.chrome.driver";
	public static String CHROME_WEB_DRIVER_EXE_PATH         = "webdrivers" + File.separator + "chromedriver.exe";
	
	// Firefox webdriver system property and exe path
	public static String FIREFOX_WEB_DRIVER_SYTEM_PROPERTY = "webdriver.gecko.driver";
	public static String FIREFOX_WEB_DRIVER_EXE_PATH       = "webdrivers" + File.separator + "geckodriver.exe";

	// Edge webdriver system property and exe path
	public static String EDGE_WEB_DRIVER_SYTEM_PROPERTY    = "webdriver.edge.driver";
	public static String EDGE_WEB_DRIVER_EXE_PATH          = "webdrivers" + File.separator + "MicrosoftWebDriver_17134.exe";

	// IE webdriver system property and exe path
	public static String IE_WEB_DRIVER_SYTEM_PROPERTY      = "webdriver.ie.driver";
	public static String IE_WEB_DRIVER_EXE_PATH            = "webdrivers" + File.separator + "IEDriverServer.exe";
	
	public WebDriverFactory()
	{
	}
	
	public static WebDriver startFullScreenWebDriver(String argWebBrowserName)
	{
		WebDriver driver;
		
		driver = createDriver(argWebBrowserName);
		driver.manage().window().fullscreen();
		
		return(driver);
	}
	
	public static WebDriver startWebDriver(String argWebBrowserName)
	{
		WebDriver driver;
		
		driver = createDriver(argWebBrowserName);
		
		return(driver);
	}
	
	public static WebDriver startWebDriver(String argWebBrowserName, Dimension argBrowserSize)
	{
		WebDriver driver;
		
		driver = createDriver(argWebBrowserName);
		driver.manage().window().setSize(argBrowserSize);
		
		return(driver);
	}
	
	public static WebDriver startWebDriver(String argWebBrowserName, Point argBrowserPosition)
	{
		WebDriver driver;
		
		driver = createDriver(argWebBrowserName);
		driver.manage().window().setPosition(argBrowserPosition);
		
		return(driver);
	}
	
	public static WebDriver startWebDriver(String argWebBrowserName, Dimension argBrowserSize, Point argBrowserPosition)
	{
		WebDriver driver;
		
		driver = createDriver(argWebBrowserName);
		driver.manage().window().setPosition(argBrowserPosition);
		driver.manage().window().setSize(argBrowserSize);
		
		return(driver);
	}
	
	public static WebDriver startWebDriver(String argWebBrowserName, int width, int height)
	{
		WebDriver driver;
		
		driver = createDriver(argWebBrowserName);
		driver.manage().window().setSize(new Dimension(width, height));
		
		return(driver);
	}
	
	public static WebDriver startWebDriver(String argWebBrowserName, int width, int height, int x, int y)
	{
		WebDriver driver;
		
		driver = createDriver(argWebBrowserName);
		driver.manage().window().setPosition(new Point(x, y));
		driver.manage().window().setSize(new Dimension(width, height));
		
		return(driver);
	}
	
	
	
	public static WebDriver startFullScreenWebDriver(String argWebBrowserName, String argPageUrl)
	{
		WebDriver driver;
		
		driver = createDriver(argWebBrowserName);
		driver.manage().window().fullscreen();
		driver.get(argPageUrl);
		
		return(driver);
	}
	
	public static WebDriver startWebDriver(String argWebBrowserName, String argPageUrl)
	{
		WebDriver driver;
		
		driver = createDriver(argWebBrowserName);
		driver.get(argPageUrl);
		
		return(driver);
	}
	
	public static WebDriver startWebDriver(String argWebBrowserName, String argPageUrl, Dimension argBrowserSize)
	{
		WebDriver driver;
		
		driver = createDriver(argWebBrowserName);
		driver.manage().window().setSize(argBrowserSize);
		driver.get(argPageUrl);
		
		return(driver);
	}
	
	public static WebDriver startWebDriver(String argWebBrowserName, String argPageUrl, Point argBrowserPosition)
	{
		WebDriver driver;
		
		driver = createDriver(argWebBrowserName);
		driver.manage().window().setPosition(argBrowserPosition);
		driver.get(argPageUrl);
		
		return(driver);
	}
	
	public static WebDriver startWebDriver(String argWebBrowserName, String argPageUrl, Dimension argBrowserSize, Point argBrowserPosition)
	{
		WebDriver driver;
		
		driver = createDriver(argWebBrowserName);
		driver.manage().window().setPosition(argBrowserPosition);
		driver.manage().window().setSize(argBrowserSize);
		driver.get(argPageUrl);
		
		return(driver);
	}
	
	public static WebDriver startWebDriver(String argWebBrowserName, String argPageUrl, int width, int height)
	{
		WebDriver driver;
		
		driver = createDriver(argWebBrowserName);
		driver.manage().window().setSize(new Dimension(width, height));
		driver.get(argPageUrl);
		
		return(driver);
	}
	
	public static WebDriver startWebDriver(String argWebBrowserName, String argPageUrl, int width, int height, int x, int y)
	{
		WebDriver driver;
		
		driver = createDriver(argWebBrowserName);
		driver.manage().window().setPosition(new Point(x, y));
		driver.manage().window().setSize(new Dimension(width, height));
		driver.get(argPageUrl);
		
		return(driver);
	}
	
	public static WebDriver createDriver(String argWebBrowserName)
	{
		WebDriver web_driver = null;
		
		switch (argWebBrowserName.toLowerCase())
		{
			case "chrome":
			{
				System.setProperty(CHROME_WEB_DRIVER_SYTEM_PROPERTY, CHROME_WEB_DRIVER_EXE_PATH);
				web_driver = new ChromeDriver();
				
				break;
			}
			case "firefox":
			{
				System.setProperty(FIREFOX_WEB_DRIVER_SYTEM_PROPERTY, FIREFOX_WEB_DRIVER_EXE_PATH);
				web_driver = new FirefoxDriver();
				
				break;
			}
			case "edge":
			{
				System.setProperty(EDGE_WEB_DRIVER_SYTEM_PROPERTY, EDGE_WEB_DRIVER_EXE_PATH);
				web_driver = new EdgeDriver();
				
				break;
			}
			case "ie":
			{
				System.setProperty(IE_WEB_DRIVER_SYTEM_PROPERTY, IE_WEB_DRIVER_EXE_PATH);
				web_driver = new InternetExplorerDriver();
				
				break;
			}
			default:
			{
				System.setProperty(CHROME_WEB_DRIVER_SYTEM_PROPERTY, CHROME_WEB_DRIVER_EXE_PATH);
				web_driver = new ChromeDriver();
			}
		}
		
		return(web_driver);
	}
}
