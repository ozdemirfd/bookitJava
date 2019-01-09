package com.bookit.step_definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ScheduleStepDefinitions {

	@When("go to my schedule")
	public void go_to_my_schedule() {
		 System.out.println("going to the scheedule");
	}

	@Then("I should be able to see the reservation for my team")
	public void i_should_be_able_to_see_the_reservation_for_my_team() {
		 System.out.println("I can see reservation for my team");
	}

}
