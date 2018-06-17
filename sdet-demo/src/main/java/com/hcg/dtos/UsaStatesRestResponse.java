package com.hcg.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsaStatesRestResponse 
{
	@JsonProperty("messages")
	private List<String> messages;
	
	@JsonProperty("result")
	private List<State> states;

	
	public List<String> getMessages()
	{
		return messages;
	}

	public void setMessages(List<String> messages) 
	{
		this.messages = messages;
	}

	public List<State> getStates() 
	{
		return states;
	}

	public void setStates(List<State> states) 
	{
		this.states = states;
	}
	
}
