package com.hcg.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hcg.dtos.State;
import com.hcg.models.SearchResults;
import com.hcg.services.UsaStatesService;

@Controller
public class IndexPageController 
{
	@Autowired
	private UsaStatesService usaStatesService;
    
    @GetMapping({"/", "/index"})
    public String indexPageView(Model model) 
    {
        model.addAttribute("searchResults", new SearchResults());
        
        return "index";
    }

    @PostMapping("/index")
    public String searchSubmit(@Valid SearchResults searchResults, BindingResult bindingResult, Model model) 
    {
    	System.out.println("Inside searchSubmit()");
    	System.out.println("  searchString = " + searchResults.getSearchString());
    	
    	if (bindingResult.hasErrors()) {
			return "index";
		}
    	
    	State state = usaStatesService.findStateByNameOrAbbr(searchResults.getSearchString());
    	
    	if (state != null)
    	{
	    	System.out.println("state.getName()         = " + state.getName()); 
	    	System.out.println("state.getAbbr()         = " + state.getAbbr());
	    	System.out.println("state.getLargest_city() = " + state.getLargest_city());
	    	System.out.println("state.getCapital()      = " + state.getCapital());
	    	System.out.println("state.getCountry()      = " + state.getCountry());
    	}
    	
    	searchResults.setState(state);
    	
    	model.addAttribute("searchResults", searchResults);
    	
    	System.out.println("Outside searchSubmit()");
    	
        return "search-result";
    }
    

    /*
    @PostMapping("/index")
    public String searchSubmit(@ModelAttribute SearchResults searchResults) 
    {
    	System.out.println("Inside searchSubmit()");
    	System.out.println("  searchString = " + searchResults.getSearchString());
    	
    	State state = usaStatesService.findStateByNameOrAbbr(searchResults.getSearchString());
    	
    	if (state != null)
    	{
	    	System.out.println("state.getName()         = " + state.getName()); 
	    	System.out.println("state.getAbbr()         = " + state.getAbbr());
	    	System.out.println("state.getLargest_city() = " + state.getLargest_city());
	    	System.out.println("state.getCapital()      = " + state.getCapital());
	    	System.out.println("state.getCountry()      = " + state.getCountry());
    	}
    	
    	
    	searchResults.setState(state);
    	
    	System.out.println("Outside searchSubmit()");
    	
        return "search-result";
    }
    */
    
}
