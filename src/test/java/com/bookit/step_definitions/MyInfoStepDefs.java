package com.bookit.step_definitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.bookit.pages.SelfPage;
import com.bookit.pages.SignInPage;
import com.bookit.pages.TeamPage;
import com.bookit.utilities.BrowserUtils;
import com.bookit.utilities.ConfigurationReader;
import com.bookit.utilities.DBUtils;
import com.bookit.utilities.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyInfoStepDefs {

	@Given("user logs in using {string}  {string}")
	public void user_logs_in_using(String email, String password) {
		  Driver.getDriver().get(ConfigurationReader.getProperty("url"));
		    Driver.getDriver().manage().window().maximize();
		    SignInPage signInPage = new SignInPage();
		    signInPage.email.sendKeys(email);
		    signInPage.password.sendKeys(password);
		    signInPage.signInButton.click();
		    BrowserUtils.waitFor(2);
	}

	@When("user is on self page")
	public void user_is_on_self_page() {
	SelfPage selfPage=new SelfPage();
	selfPage.goToSelf();
	BrowserUtils.waitFor(2);
	}

	@Then("user info should match with the database records for {string}")
	public void user_info_should_match_with_the_database_records_for(String email) {
	    //writing our query
	    String query = "SELECT firstname,lastname,role\n" + 
	        "FROM users\n" + 
	        "WHERE email = '"+email+"'";
	    //get value from database and assign to map
	    Map<String,Object> result = DBUtils.getRowMap(query);
	    
	    //assigning database values to variables
	    String expectedFirstName = (String) result.get("firstname");
	    String expectedLastName = (String) result.get("lastname");
	    String expectedRole = (String) result.get("role");
	    
	    String expectedFullname =expectedFirstName+" "+expectedLastName;
	    //----------------UI----------------------------
	    
	    SelfPage selfPage = new SelfPage();
	    
	    //wait until got the user information table
	    BrowserUtils.waitFor(2);

	    //getting values from UI and assigning to variables 
	    String actualFullName = selfPage.name.getText();
	    String actualRole = selfPage.role.getText();
	    
	    
	    System.out.println(actualFullName);
	    System.out.println(actualRole);
	    
	    
	    // compare UI and DATABASE values  
	    Assert.assertEquals(actualFullName, expectedFullname);
	    Assert.assertEquals(actualRole, expectedRole);

	    
	  }
	@When("user is on the my team page")
	public void user_is_on_the_my_team_page() {
		SelfPage selfPage=new SelfPage();
		selfPage.goToTeam();
		BrowserUtils.waitFor(2);
	}

	@Then("team info should match with the database records {string}")
	public void team_info_should_match_with_the_database_records(String email) {
		
		
	    //writing our query
	    String query = "Select firstname,role From users\n" + 
	    		"where team_id=(Select team_id\n" + 
	    		"From users\n" + 
	    		"where email='"+email+"')";
	    //get value from database and assign to map
	    List<Map<String,Object>> result = DBUtils.getQueryResultMap(query);
	    
		TeamPage teamPage=new TeamPage();
		
		List<String> actualNames= new ArrayList<>();
		for(WebElement el:teamPage.teamMemberNames) {
		actualNames.add(el.getText());
		
		}
		
		List<String> actualRoles=new ArrayList<>();
		
		for(WebElement el:teamPage.teamMemberRoles) {
			actualRoles.add(el.getText());
			
		}
		
		Assert.assertEquals(result.size(),actualNames.size());
		Assert.assertEquals(result.size(),actualRoles.size());
		
		
		
		 for(Map<String,Object> key: result) {
			 String firstname=(String) key.get("firstname");	
			 String role=(String) key.get("role");
			 
			 Assert.assertTrue(actualRoles.contains(role));
			 Assert.assertTrue(actualNames.contains(firstname));
			 
		 }

	 
	}
	@When("user is on the my self page")
	public void user_is_on_the_my_self_page() {
	   SelfPage selfPage=new SelfPage();
	   selfPage.goToTeam();
	}


	
	
}
