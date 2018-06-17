package com.hcg.services;

import java.util.List;
import com.hcg.dtos.State;

public interface UsaStatesService 
{
	public List<State> findAll();
	public State       findById(int id);
	public State       findByName(String name);
	public State       findByAbbreviation(String abbreviation);
	public State       findStateByNameOrAbbr(String searchString);
	public String      findLargestCityByStateNameOrAbbr(String searchString);
	public String      findCapitalByStateNameOrAbbr(String searchString);
	public void        reload();
}
