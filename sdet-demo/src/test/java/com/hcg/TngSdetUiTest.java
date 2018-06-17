package com.hcg;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcg.dtos.State;
import com.hcg.dtos.UsaStatesDTO;
import com.hcg.framework.selenium.WebDriverFactory;
import com.hcg.framework.selenium.pages.IndexPage;
import com.hcg.framework.selenium.pages.SearchResultPage;
import com.hcg.framework.testng.BaseTestCase;

@SpringBootTest(classes = SdetDemoApplication.class)
public class TngSdetUiTest extends BaseTestCase
{
	
	@Test
	@Parameters("webDriverName")
	public void testUserInterface(String webDriverName) throws InterruptedException, IllegalArgumentException
	{
		IndexPage        indexPage;
		SearchResultPage searchResultPage;
		
		if ((webDriverName == null) || (webDriverName.isEmpty()))
			throw new IllegalArgumentException("Error: invalid webDriverName parameter.");
		
		webDriver = WebDriverFactory.createDriver(webDriverName);
		
		indexPage = new IndexPage(webDriver, 1400, 1000);
		
		indexPage.navigate();
		indexPage.assertLandedOnPage();
		
		// Test for incorrect search string values
		indexPage.enterSearchString("");
		searchResultPage = indexPage.clickOnSubmitButton();
		assertThat("Unexpected error message: ", indexPage.getErrorMessageText(), notNullValue());
		assertThat("Unexpected error message: ", indexPage.getErrorMessageText(), anyOf(containsString("must match \"[a-zA-Z][a-zA-Z ]*[a-zA-Z]\""), containsString("size must be between 2 and 20")));
		
		indexPage.enterSearchString("a");
		searchResultPage = indexPage.clickOnSubmitButton();
		assertThat("Unexpected error message: ", indexPage.getErrorMessageText(), notNullValue());
		assertThat("Unexpected error message: ", indexPage.getErrorMessageText(), anyOf(containsString("must match \"[a-zA-Z][a-zA-Z ]*[a-zA-Z]\""), containsString("size must be between 2 and 20")));
		
		indexPage.enterSearchString("26405");
		searchResultPage = indexPage.clickOnSubmitButton();
		assertThat("Unexpected error message: ", indexPage.getErrorMessageText(), notNullValue());
		assertThat("Unexpected error message: ", indexPage.getErrorMessageText(), containsString("must match \"[a-zA-Z][a-zA-Z ]*[a-zA-Z]\""));
		
		indexPage.enterSearchString("OregonOregonOregonOregon");
		searchResultPage = indexPage.clickOnSubmitButton();
		assertThat("Unexpected error message: ", indexPage.getErrorMessageText(), notNullValue());
		assertThat("Unexpected error message: ", indexPage.getErrorMessageText(), containsString("size must be between 2 and 20"));
		
		indexPage.enterSearchString("Oregon");
		searchResultPage = indexPage.clickOnSubmitButton();
		searchResultPage.assertLandedOnPage();
		assertThat("Unexpected state capital result: ", searchResultPage.getStateCapitalLabelText(), equalTo("Salem"));
		assertThat("Unexpected state largest city result: ", searchResultPage.getStateLargestCityLabelText(), equalTo("Portland"));
		
		indexPage = searchResultPage.clickOnSubmitAnotherSearchLink();
		indexPage.assertLandedOnPage();

		indexPage.enterSearchString("CA");
		searchResultPage = indexPage.clickOnSubmitButton();
		searchResultPage.assertLandedOnPage();
		assertThat("Unexpected state capital result: ", searchResultPage.getStateCapitalLabelText(), equalTo("Sacramento"));
		assertThat("Unexpected state largest city result: ", searchResultPage.getStateLargestCityLabelText(), equalTo("Los Angeles"));
		
		indexPage = searchResultPage.clickOnSubmitAnotherSearchLink();
		indexPage.assertLandedOnPage();
	}
}