package com.hcg.models;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.hcg.dtos.State;

public class SearchResults {
	
	@NotNull
	@Size(min=2, max=20)
	@Pattern(regexp = "[a-zA-Z][a-zA-Z ]*[a-zA-Z]")
	private String searchString;
	
	private State  state;
	
	public String getSearchString() 
	{
		return searchString;
	}
	
	public void setSearchString(String searchString) 
	{
		this.searchString = searchString;
	}
	
	public State getState()
	{
		return state;
	}
	
	public void setState(State state)
	{
		this.state = state;
	}
	
}
