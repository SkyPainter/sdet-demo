package com.hcg.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsaStatesDTO {
	
	@JsonProperty("RestResponse")
	private UsaStatesRestResponse restResponse;

	public UsaStatesRestResponse getRestResponse() 
	{
		return restResponse;
	}

	public void setRestResponse(UsaStatesRestResponse restResponse) 
	{
		this.restResponse = restResponse;
	}
	
}
