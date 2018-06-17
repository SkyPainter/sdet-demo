package com.hcg.framework.selenium;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public abstract class AbstractPageObjectModel
{
	protected WebDriver          driver;
	protected JavascriptExecutor jsExecutor;
	protected WebDriverWait      webDriverWait;
	
	
	public AbstractPageObjectModel(String argWebBrowserName)
	{
		this(WebDriverFactory.startFullScreenWebDriver(argWebBrowserName));
	}
	
	public AbstractPageObjectModel(String argWebBrowserName, Dimension argBrowserSize)
	{
		this(WebDriverFactory.startWebDriver(argWebBrowserName, argBrowserSize));
	}
	
	public AbstractPageObjectModel(String argWebBrowserName, Point argBrowserPosition)
	{
		this(WebDriverFactory.startWebDriver(argWebBrowserName, argBrowserPosition));
	}
	
	public AbstractPageObjectModel(String argWebBrowserName, Dimension argBrowserSize, Point argBrowserPosition)
	{
		this(WebDriverFactory.startWebDriver(argWebBrowserName, argBrowserSize, argBrowserPosition));
	}
	
	public AbstractPageObjectModel(String argWebBrowserName, int width, int height)
	{
		this(WebDriverFactory.startWebDriver(argWebBrowserName, width, height));
	}
	
	public AbstractPageObjectModel(String argWebBrowserName, int width, int height, int x, int y)
	{
		this(WebDriverFactory.startWebDriver(argWebBrowserName, width, height, x, y));
	}
	
	public AbstractPageObjectModel(WebDriver argWebDriver)
	{
		driver = argWebDriver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		jsExecutor = (JavascriptExecutor) driver;
		webDriverWait = new WebDriverWait(driver, 30);
	}
	
	public AbstractPageObjectModel(WebDriver argWebDriver, Dimension argBrowserSize)
	{
		this(argWebDriver);
		driver.manage().window().setSize(argBrowserSize);
	}
	
	public AbstractPageObjectModel(WebDriver argWebDriver, Point argBrowserPosition)
	{
		this(argWebDriver);
		driver.manage().window().setPosition(argBrowserPosition);
	}
	
	public AbstractPageObjectModel(WebDriver argWebDriver, Dimension argBrowserSize, Point argBrowserPosition)
	{
		this(argWebDriver);
		driver.manage().window().setPosition(argBrowserPosition);
		driver.manage().window().setSize(argBrowserSize);
	}
	
	public AbstractPageObjectModel(WebDriver argWebDriver, int width, int height)
	{
		this(argWebDriver);
		driver.manage().window().setSize(new Dimension(width, height));
	}
	
	public AbstractPageObjectModel(WebDriver argWebDriver, int width, int height, int x, int y)
	{
		this(argWebDriver);
		driver.manage().window().setPosition(new Point(x, y));
		driver.manage().window().setSize(new Dimension(width, height));
	}
	
	
	
	//
	// Helper methods specific to the WebDriver object
	//
	public WebDriver getWebDriver()
	{
		return(driver);
	}
	
	public void close()
	{
		driver.close();
	}
	
	public JavascriptExecutor getJsExecutor()
	{
		return(jsExecutor);
	}

	
	//
	// Navigation helper methods
	//
	public abstract void navigate();
	
	public void navigateTo(String argUrl)
	{
		driver.navigate().to(argUrl);
	}
	
	public void back()
	{
		driver.navigate().back();
	}
	
	public void forward()
	{
		driver.navigate().forward();
	}
	
	//
	// WebElement specific helper methods 
	public void clickElement(WebElement argElement)
	{
		argElement.click();
	}
	
	//
	// Assertion helper methods
	//
	public void assertLandedOnPage(String argExpectedURL, String argMessage)
	{
		assertThat(driver.getCurrentUrl().toLowerCase(), is(equalTo(argExpectedURL.toLowerCase())));
		
		/*
		if (driver != null)
		{
			if (argMessage != null) {
				Assert.assertEquals(driver.getCurrentUrl().toLowerCase(), argExpectedURL.toLowerCase(), argMessage);
			}
			else {
				Assert.assertEquals(driver.getCurrentUrl().toLowerCase(), argExpectedURL.toLowerCase());
			}
		}
		*/
	}
	
	public void assertUrlIsReachable(String argUrl)
	{
		assertThat(isUrlReachable(argUrl), is(equalTo(true)));
	}
	
	//
	// Timeout helper methods
	//
	public void implicitlyWait(long argTime, TimeUnit argTimeUnit)
	{
		driver.manage().timeouts().implicitlyWait(argTime, argTimeUnit);
	}
	
	public WebElement waitUntilElementIsVisible(String argXpath)
	{
		return(webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(argXpath))));
	}
	
	//
	// URL validation helper methods
	//
    private boolean isUrlReachable(String argUrl)
	{
		HttpClient   client      = new DefaultHttpClient();
		HttpGet      request     = new HttpGet(argUrl);
		HttpResponse response    = null;
		boolean      isReachable = false;
		
        try 
        {
        	response = client.execute(request);
            
            if (response.getStatusLine().getStatusCode() == 200)
            {
            	isReachable = true;
            }
        } 
        catch (IOException e)
        {
        	e.printStackTrace();
        }
        
        return(isReachable);
	}
}

