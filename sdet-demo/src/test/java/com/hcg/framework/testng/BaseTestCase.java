package com.hcg.framework.testng;

import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

// @ContextConfiguration(locations={"classpath:spring-test-config.xml"})
public class BaseTestCase extends AbstractTestNGSpringContextTests
{
	protected WebDriver webDriver;
	protected String    websiteUrl;

	protected ObjectMapper mapper       = new ObjectMapper();
	protected RestTemplate restTemplate = new RestTemplate();
	
    @BeforeClass
    public void setupWebDriver()
    {
    }	


    @BeforeMethod
    public void loadWebApplication() 
    {
    }

    @AfterMethod
    public void deleteAllCookies() 
    {
    	if (webDriver != null)
    	{
    		webDriver.manage().deleteAllCookies();
    	}
    }

    @AfterClass
    public void suiteTearDown()
    {
    	if (webDriver != null)
    	{
    		webDriver.close();
    	}
    	
    	webDriver = null;
    }
}
