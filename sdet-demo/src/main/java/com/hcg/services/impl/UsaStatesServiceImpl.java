package com.hcg.services.impl;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcg.dtos.State;
import com.hcg.dtos.UsaStatesDTO;
import com.hcg.services.UsaStatesService;

@Service
public class UsaStatesServiceImpl implements UsaStatesService {
	
	private UsaStatesDTO dto;
	
	public UsaStatesServiceImpl()
	{
		loadUsaStates();
	}
	
	private void loadUsaStates()
	{
		try 
		{
			ObjectMapper mapper       = new ObjectMapper();
			RestTemplate restTemplate = new RestTemplate();
			
			ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://services.groupkt.com/state/get/USA/all", String.class);
			
			if ((responseEntity != null) && (responseEntity.getStatusCode() == HttpStatus.OK))
				dto = mapper.readValue(responseEntity.getBody(), UsaStatesDTO.class);
			else
				dto = null;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public List<State> findAll()
	{
		List<State> states = null;
		
		if (dto != null)
			states = dto.getRestResponse().getStates();
		
		return(states);
	}
	
	public State findById(int id)
	{
		State state = null;
		
		if (dto != null)
		{
			List<State> states = dto.getRestResponse().getStates().stream().filter(s->s.getId() == id).collect(Collectors.toList());
			
			if ((states != null) && (states.size() > 0))
				state = states.get(0);
		}
		
		return(state);
	}
	
	public State findByName(String name)
	{
		State state = null;
		
		if (dto != null)
		{
			List<State> states = dto.getRestResponse().getStates().stream().filter(s->s.getName().equals(name)).collect(Collectors.toList());
			
			if ((states != null) && (states.size() > 0))
				state = states.get(0);
		}
		
		return(state);
	}
	
	public State findByAbbreviation(String abbreviation)
	{
		State state = null;
		
		if (dto != null)
		{
			List<State> states = dto.getRestResponse().getStates().stream().filter(s->s.getAbbr().equals(abbreviation)).collect(Collectors.toList());
			
			if ((states != null) && (states.size() > 0))
				state = states.get(0);
		}
		
		return(state);
	}
	
	public State findStateByNameOrAbbr(String searchString)
	{
		State state = null;
		
		if (dto != null)
		{
			List<State> states = dto.getRestResponse().getStates()
					                .stream().filter( s-> ((s.getName().equals(searchString)) || (s.getAbbr().equals(searchString))))
					                .collect(Collectors.toList());
			
			if ((states != null) && (states.size() > 0))
				state = states.get(0);
		}
		
		return(state);
	}
	
	public String findLargestCityByStateNameOrAbbr(String searchString)
	{
		String stateCity = null;
		
		if (dto != null)
		{
			List<State> states = dto.getRestResponse().getStates()
					                .stream().filter( s-> ((s.getName().equals(searchString)) || (s.getAbbr().equals(searchString))))
					                .collect(Collectors.toList());
			
			if ((states != null) && (states.size() > 0))
				stateCity = states.get(0).getLargest_city();
		}
		
		return(stateCity);
	}
	
	public String findCapitalByStateNameOrAbbr(String searchString)
	{
		String stateCapital = null;
		
		if (dto != null)
		{
			List<State> states = dto.getRestResponse().getStates()
					                .stream().filter( s-> ((s.getName().equals(searchString)) || (s.getAbbr().equals(searchString))))
					                .collect(Collectors.toList());
			
			if ((states != null) && (states.size() > 0))
				stateCapital = states.get(0).getCapital();
		}
		
		return(stateCapital);
	}
	
	
	
	public void reload()
	{
		loadUsaStates();
	}
	
	
	
	public static void main(String args[])
	{
		UsaStatesServiceImpl service = new UsaStatesServiceImpl();
		State state = null;
		
		System.out.println("service.findAll().get(5).getName()          = " + service.findAll().get(5).getName());
		System.out.println("service.findById(7).getName()               = " + service.findById(7).getName());
		System.out.println("service.findByName('Puerto Rico').getName() = " + service.findByName("Puerto Rico").getName());
		System.out.println("service.findByAbbreviation('AZ').getName()  = " + service.findByAbbreviation("AZ").getName());
		System.out.println("service.findLargestCityByStateNameOrAbbr('Arizona') = " + service.findLargestCityByStateNameOrAbbr("Arizona"));
		System.out.println("service.findLargestCityByStateNameOrAbbr('AZ')      = " + service.findLargestCityByStateNameOrAbbr("AZ"));
		System.out.println("service.findCapitalByStateNameOrAbbr('California')  = " + service.findCapitalByStateNameOrAbbr("California"));
		System.out.println("service.findCapitalByStateNameOrAbbr('CA')          = " + service.findCapitalByStateNameOrAbbr("CA"));

		System.out.println();
		System.out.println("Searching for Virginia");
		state = service.findStateByNameOrAbbr("Virginia");
		System.out.println("state.getName()        = " + state.getName());
		System.out.println("state.getLarget_city() = " + state.getLargest_city());

		System.out.println();
		System.out.println("Searching for VA");
		state = service.findStateByNameOrAbbr("VA");
		System.out.println("state.getName()        = " + state.getName());
		System.out.println("state.getLarget_city() = " + state.getLargest_city());
	}
	
	
	
	
	/*
	public static void main(String args[])
	{
		ObjectMapper mapper = new ObjectMapper();
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://services.groupkt.com/state/get/USA/all", String.class);
		
		System.out.println("responseEntity.getBody() = " + responseEntity.getBody());
		
		try 
		{
			UsaStatesDTO dto = mapper.readValue(responseEntity.getBody(), UsaStatesDTO.class);
			
			System.out.println("dto.getRestResponse().getMessages().get(0) = " + dto.getRestResponse().getMessages().get(0));
			System.out.println("dto.getRestResponse().getStates().get(3).getName() = " + dto.getRestResponse().getStates().get(3).getName());
		} 
		catch (JsonParseException e) 
		{
			e.printStackTrace();
		} 
		catch (JsonMappingException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("");
		System.out.println("Bye Bye!");
	}
	*/

}
