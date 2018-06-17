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
import com.hcg.framework.testng.BaseTestCase;

@SpringBootTest(classes = com.hcg.SdetDemoApplication.class)
public class TngSdetRestApiTest extends BaseTestCase
{
	@Test
	public void testRestAPI()
	{
		UsaStatesDTO dto;
		
		try 
		{
			// ObjectMapper mapper       = new ObjectMapper();
			// RestTemplate restTemplate = new RestTemplate();
			
			ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://services.groupkt.com/state/get/USA/all", String.class);
			
			Assert.assertNotNull(responseEntity, "Response is null");
			Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
			Assert.assertNotNull(responseEntity.getBody(), "Response body is null");
			
			dto = mapper.readValue(responseEntity.getBody(), UsaStatesDTO.class);
			Assert.assertNotNull(dto, "Data transfer object is null");
			
			Assert.assertNotNull(dto.getRestResponse().getMessages(), "Response messages list is null. ");
			Assert.assertEquals(dto.getRestResponse().getMessages().size(), 1, "Unexpected number of messages returned: ");
			Assert.assertEquals(dto.getRestResponse().getMessages().get(0), "Total [55] records found.", "Unexpected error message content found: ");
			
			
			Assert.assertNotNull(dto.getRestResponse().getStates(), "Response states list is null. ");
			Assert.assertEquals(dto.getRestResponse().getStates().size(), 55, "Unexpected number of states returned: ");
			
			List<State> states = dto.getRestResponse().getStates();
					
			states.forEach((state) -> {
				
				//
				// For purposes of the demo.
				// Switching to hamcrest assertions instead of TestNG assertions for this section of code.
				//
				assertThat("Unexpected state id type: ", Long.valueOf(state.getId()), instanceOf(Long.class));
				assertThat("Unexpected state id value: ", Long.valueOf(state.getId()), greaterThan((long)0));
				assertThat("Unexpected state id value: ", Long.valueOf(state.getId()), lessThan((long)56));

				assertThat("Unexpected state country value: ", state.getCountry(), notNullValue());
				assertThat("Unexpected state name value: ", state.getName(), notNullValue());
				assertThat("Unexpected state abbreviation value: ", state.getAbbr(), notNullValue());
				assertThat("Unexpected state area value: ", state.getArea(), notNullValue());
				// assertThat("Unexpected state largest city value: ", state.getLargest_city(), notNullValue());
				assertThat("Unexpected state capital value: ", state.getCapital(), notNullValue());
				
			});
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
