package com.bookit.step_definitions;

import org.junit.Assert;

import com.bookit.pages.MapPage;
import com.bookit.pages.MySelfPage;
import com.bookit.utilities.Driver;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class AccountInfoStepDefs {

	@When("the user goes to myself page")
	public void the_user_goes_to_myself_page() {
	 // new MapPage().goToSelf(); if you use one time,it is ok
		
	MapPage mapPage=new MapPage();
	mapPage.goToSelf();
	}

	@Then("team name {string} should be displayed")
	public void team_name_should_be_displayed(String expectesdTeamName) {
		
	 MySelfPage myselfpage=new MySelfPage();
	 String actualTeamName= myselfpage.teamName.getText();
	 System.out.println(actualTeamName);
	 Assert.assertEquals(expectesdTeamName, actualTeamName);
	 
		
	}

}
