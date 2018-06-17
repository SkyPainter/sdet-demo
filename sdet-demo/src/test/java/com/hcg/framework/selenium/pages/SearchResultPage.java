package com.hcg.framework.selenium.pages;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.startsWith;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hcg.framework.selenium.AbstractPageObjectModel;
import com.hcg.framework.selenium.WebDriverFactory;


public class SearchResultPage extends AbstractPageObjectModel {
	
	public static String TEMPLATE_PAGE_URL = "http://localhost:8080/index";
	public static String EXPECTED_TITLE_STARTS_WITH = "Search Results for";
	
	//
	// Page's WebElement XPaths
	//
	public static final String TITLE_XPATH                        = "/html/body/div/div[1]/div/h2";
	public static final String STATE_CAPITAL_PARAGRAPH_XPATH      = "/html/body/div/div[2]/div/span[2]";
	public static final String STATE_LARGEST_CITY_PARAGRAPH_XPATH = "/html/body/div/div[3]/div/span[2]";
	public static final String SUBMIT_ANOTHER_SEARCH_LINK_XPATH   = "/html/body/div/div[4]/a";
	
	@FindBy(xpath = TITLE_XPATH) 
	private WebElement title;
	
	@FindBy(xpath = STATE_CAPITAL_PARAGRAPH_XPATH)
	private WebElement stateCapitalLabel;
	
	@FindBy(xpath = STATE_LARGEST_CITY_PARAGRAPH_XPATH)
	private WebElement stateLargestCityLabel;
	
	@FindBy(xpath = SUBMIT_ANOTHER_SEARCH_LINK_XPATH)
	private WebElement submitAnotherSerarchLink;
	

	//
	// Constructors
	//
	public SearchResultPage(WebDriver argWebDriver)
	{
		super(argWebDriver);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	/*
	public SearchResultPage(String argWebBrowserName)
	{
		super(argWebBrowserName);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	public SearchResultPage(WebDriver argWebDriver, Dimension argBrowserSize)
	{
		super(argWebDriver, argBrowserSize);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	public SearchResultPage(WebDriver argWebDriver, Point argBrowserPosition)
	{
		super(argWebDriver, argBrowserPosition);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	public SearchResultPage(WebDriver argWebDriver, Dimension argBrowserSize, Point argBrowserPosition)
	{
		super(argWebDriver, argBrowserSize, argBrowserPosition);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	public SearchResultPage(WebDriver argWebDriver, int width, int height)
	{
		super(argWebDriver, width, height);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	public SearchResultPage(WebDriver argWebDriver, int width, int height, int x, int y)
	{
		super(argWebDriver, width, height, x, y);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	public SearchResultPage(String argWebBrowserName, Dimension argBrowserSize)
	{
		this(WebDriverFactory.startWebDriver(argWebBrowserName, argBrowserSize));
	}
	
	public SearchResultPage(String argWebBrowserName, Point argBrowserPosition)
	{
		this(WebDriverFactory.startWebDriver(argWebBrowserName, argBrowserPosition));
	}
	
	public SearchResultPage(String argWebBrowserName, Dimension argBrowserSize, Point argBrowserPosition)
	{
		this(WebDriverFactory.startWebDriver(argWebBrowserName, argBrowserSize, argBrowserPosition));
	}
	
	public SearchResultPage(String argWebBrowserName, int width, int height)
	{
		this(WebDriverFactory.startWebDriver(argWebBrowserName, width, height));
	}
	
	public SearchResultPage(String argWebBrowserName, int width, int height, int x, int y)
	{
		this(WebDriverFactory.startWebDriver(argWebBrowserName, width, height, x, y));
	}
	*/
	
	
	//
	// Setters and Getters
	//
	public WebElement getTitle() {
		return(waitUntilElementIsVisible(TITLE_XPATH));
	}
	
	public WebElement getStateCapitalLabel() {
		return(waitUntilElementIsVisible(STATE_CAPITAL_PARAGRAPH_XPATH));
	}

	public WebElement getStateLargestCityLabel() {
		return(waitUntilElementIsVisible(STATE_LARGEST_CITY_PARAGRAPH_XPATH));
	}

	public WebElement getSubmitAnotherSearchLink() {
		return(waitUntilElementIsVisible(SUBMIT_ANOTHER_SEARCH_LINK_XPATH));
	}
	
	
	//
	// Convenience methods
	//
	public void navigate()
	{
		navigateTo(TEMPLATE_PAGE_URL);
	}
	
	public IndexPage clickOnSubmitAnotherSearchLink()
	{
		clickElement(getSubmitAnotherSearchLink());
		
		return(new IndexPage(getWebDriver()));
	}
	
	public String getTitleText()
	{
		return(getTitle().getText());
	}
	
	public String getStateCapitalLabelText()
	{
		return(getStateCapitalLabel().getText());
	}
	
	public String getStateLargestCityLabelText()
	{
		return(getStateLargestCityLabel().getText());
	}
	
	public void assertLandedOnPage()
	{
		assertLandedOnPage(TEMPLATE_PAGE_URL, "Unexpected Fox Website account page URL: ");
		// System.out.println("getTitleText() = " + getTitleText());
		assertThat(getTitleText(), startsWith(EXPECTED_TITLE_STARTS_WITH));
		

		// Assert.assertEquals(getTitleText(), EXPECTED_TITLE, "Unexpected page title: ");
	}

}
