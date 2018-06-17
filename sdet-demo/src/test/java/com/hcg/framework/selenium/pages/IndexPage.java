package com.hcg.framework.selenium.pages;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hcg.framework.selenium.AbstractPageObjectModel;
import com.hcg.framework.selenium.WebDriverFactory;

public class IndexPage extends AbstractPageObjectModel {
	
	public static String INDEX_PAGE_URL = "http://localhost:8080/index";
	public static String EXPECTED_TITLE = "Search for largest state city and state capital";
	
	//
	// Page's WebElement XPaths
	//
	public static final String TITLE_XPATH         = "/html/body/div/h2";
	public static final String SEARCH_STR_TF_XPATH = "//*[@id=\"searchString\"]";
	public static final String ERROR_MESSAGE_XPATH = "/html/body/div/form/div[1]/p[2]";
	public static final String SUBMIT_BUTTON_XPATH = "/html/body/div/form/div[2]/p/input[1]";
	public static final String RESET_BUTTON_XPATH  = "/html/body/div/form/div[2]/p/input[2]";
	
	@FindBy(xpath = TITLE_XPATH) 
	private WebElement title;
	
	@FindBy(xpath = SEARCH_STR_TF_XPATH)
	private WebElement searchStringTextField;
	
	@FindBy(xpath = ERROR_MESSAGE_XPATH)
	private WebElement errorMessageLabel;
	
	@FindBy(xpath = SUBMIT_BUTTON_XPATH)
	private WebElement submitButton;
	
	@FindBy(xpath = RESET_BUTTON_XPATH)
	private WebElement resetButton;
	

	//
	// Constructors
	//
	public IndexPage(WebDriver argWebDriver)
	{
		super(argWebDriver);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	
	public IndexPage(String argWebBrowserName)
	{
		super(argWebBrowserName);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	public IndexPage(WebDriver argWebDriver, Dimension argBrowserSize)
	{
		super(argWebDriver, argBrowserSize);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	public IndexPage(WebDriver argWebDriver, Point argBrowserPosition)
	{
		super(argWebDriver, argBrowserPosition);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	public IndexPage(WebDriver argWebDriver, Dimension argBrowserSize, Point argBrowserPosition)
	{
		super(argWebDriver, argBrowserSize, argBrowserPosition);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	public IndexPage(WebDriver argWebDriver, int width, int height)
	{
		super(argWebDriver, width, height);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	public IndexPage(WebDriver argWebDriver, int width, int height, int x, int y)
	{
		super(argWebDriver, width, height, x, y);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	public IndexPage(String argWebBrowserName, Dimension argBrowserSize)
	{
		this(WebDriverFactory.startWebDriver(argWebBrowserName, argBrowserSize));
	}
	
	public IndexPage(String argWebBrowserName, Point argBrowserPosition)
	{
		this(WebDriverFactory.startWebDriver(argWebBrowserName, argBrowserPosition));
	}
	
	public IndexPage(String argWebBrowserName, Dimension argBrowserSize, Point argBrowserPosition)
	{
		this(WebDriverFactory.startWebDriver(argWebBrowserName, argBrowserSize, argBrowserPosition));
	}
	
	public IndexPage(String argWebBrowserName, int width, int height)
	{
		this(WebDriverFactory.startWebDriver(argWebBrowserName, width, height));
	}
	
	public IndexPage(String argWebBrowserName, int width, int height, int x, int y)
	{
		this(WebDriverFactory.startWebDriver(argWebBrowserName, width, height, x, y));
	}
	
	
	//
	// Setters and Getters
	//
	public WebElement getTitle() 
	{
		// return title;
		return(waitUntilElementIsVisible(TITLE_XPATH));
	}
	
	public WebElement getSearchStringTextField() 
	{
		// return searchStringTextField;
		return(waitUntilElementIsVisible(SEARCH_STR_TF_XPATH));
	}
	
	public WebElement getErrorMessageLabel()
	{
		return(waitUntilElementIsVisible(ERROR_MESSAGE_XPATH));
	}

	public WebElement getSubmitButton() 
	{
		// return submitButton;
		return(waitUntilElementIsVisible(SUBMIT_BUTTON_XPATH));
	}

	public WebElement getResetButton()
    {
		// return resetButton;
		return(waitUntilElementIsVisible(RESET_BUTTON_XPATH));
	}
	
	
	//
	// Convenience methods
	//
	public void navigate()
	{
		navigateTo(INDEX_PAGE_URL);
	}
	
	public void enterSearchString(String searchString) throws InterruptedException
	{
		getSearchStringTextField().clear();
		getSearchStringTextField().sendKeys(searchString);
	}
	
	public SearchResultPage clickOnSubmitButton()
	{
		clickElement(getSubmitButton());
		
		return(new SearchResultPage(getWebDriver()));
	}
	
	public void clickOnResetButton()
	{
		clickElement(getResetButton());
	}
	
	public String getTitleText()
	{
		return(getTitle().getText());
	}
	
	public String getErrorMessageText() throws InterruptedException
	{
		return(getErrorMessageLabel().getText());
	}
	
	public void assertLandedOnPage()
	{
		assertLandedOnPage(INDEX_PAGE_URL, "Unexpected Fox Website index page URL: ");
		// System.out.println("getTitleText() = " + getTitleText());
		Assert.assertEquals(getTitleText(), EXPECTED_TITLE, "Unexpected page title: ");
	}

}
