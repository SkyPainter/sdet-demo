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

public class TemplatePage extends AbstractPageObjectModel {
	
	//
	// Page's URL
	//
	public static String TEMPLATE_PAGE_URL = "";
	
	//
	// Page's WebElement XPaths
	//
	public static final String TITLE_XPATH      = "";
	public static final String TEXT_LINK_XPATH  = "";
	public static final String ICON_LINK_XPATH  = "";
	public static final String IMAGE_LINK_XPATH = "";
	public static final String BUTTON_XPATH     = "";
	public static final String INPUT_TF_XPATH   = "";
	
	//
	// Page's WebElements
	//
	@FindBy(xpath = "") 
	private WebElement title;
	
	@FindBy(xpath = "")
	private WebElement textLink;
	
	@FindBy(xpath = "")
	private WebElement iconLink;
	
	@FindBy(xpath = "")
	private WebElement imageLink;
	
	@FindBy(xpath = "")
	private WebElement button;
	
	@FindBy(xpath = "")
	private WebElement inputTextField;
	

	//
	// Constructors
	//
	public TemplatePage(WebDriver argWebDriver)
	{
		super(argWebDriver);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	/*
	public TemplatePage(String argWebBrowserName)
	{
		super(argWebBrowserName);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	public TemplatePage(WebDriver argWebDriver, Dimension argBrowserSize)
	{
		super(argWebDriver, argBrowserSize);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	public TemplatePage(WebDriver argWebDriver, Point argBrowserPosition)
	{
		super(argWebDriver, argBrowserPosition);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	public TemplatePage(WebDriver argWebDriver, Dimension argBrowserSize, Point argBrowserPosition)
	{
		super(argWebDriver, argBrowserSize, argBrowserPosition);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	public TemplatePage(WebDriver argWebDriver, int width, int height)
	{
		super(argWebDriver, width, height);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	public TemplatePage(WebDriver argWebDriver, int width, int height, int x, int y)
	{
		super(argWebDriver, width, height, x, y);
		PageFactory.initElements(super.getWebDriver(), this);
	}
	
	public TemplatePage(String argWebBrowserName, Dimension argBrowserSize)
	{
		this(WebDriverFactory.startWebDriver(argWebBrowserName, argBrowserSize));
	}
	
	public TemplatePage(String argWebBrowserName, Point argBrowserPosition)
	{
		this(WebDriverFactory.startWebDriver(argWebBrowserName, argBrowserPosition));
	}
	
	public TemplatePage(String argWebBrowserName, Dimension argBrowserSize, Point argBrowserPosition)
	{
		this(WebDriverFactory.startWebDriver(argWebBrowserName, argBrowserSize, argBrowserPosition));
	}
	
	public TemplatePage(String argWebBrowserName, int width, int height)
	{
		this(WebDriverFactory.startWebDriver(argWebBrowserName, width, height));
	}
	
	public TemplatePage(String argWebBrowserName, int width, int height, int x, int y)
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

	public WebElement getTextLink() {
		return(waitUntilElementIsVisible(TEXT_LINK_XPATH));
	}

	public WebElement getIconLink() {
		return(waitUntilElementIsVisible(ICON_LINK_XPATH));
	}

	public WebElement getImageLink() {
		return(waitUntilElementIsVisible(IMAGE_LINK_XPATH));
	}

	public WebElement getButton() {
		return(waitUntilElementIsVisible(BUTTON_XPATH));
	}

	public WebElement getInputTextField() {
		return(waitUntilElementIsVisible(INPUT_TF_XPATH));
	}
	

	//
	// Convenience methods
	//
	public void navigate()
	{
		navigateTo(TEMPLATE_PAGE_URL);
	}
	
	public void clickOnTextLink()
	{
		clickElement(getTextLink());
	}
	
	public void clickOnImageLink()
	{
		clickElement(getImageLink());
	}
	
	public void clickOnImageIcon()
	{
		clickElement(getIconLink());
	}
	
	public void clickOnButton()
	{
		clickElement(getButton());
	}
	
	public void assertLandedOnPage()
	{
		assertLandedOnPage(TEMPLATE_PAGE_URL, "Unexpected Fox Website account page URL: ");
	}

}
