package com.morningstar.bdd.stepDefinitions.Common;

import com.morningstar.bdd.flows.NavigationFlow;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class NavigationStepDefinitions {
	private NavigationFlow navigationFlow;

	public NavigationStepDefinitions() {
		navigationFlow = new NavigationFlow();
	}

	@When("^I navigates to \"([^\"]*)\" inside \"([^\"]*)\" menu$")
	public void i_navigates_to_inside_menu(String subComponent, String componentType) {
		System.out.println("I navigates to " + subComponent + " inside " + componentType + " menu");
		navigationFlow.gotoComponentsItemPage(componentType, subComponent);
	}
	
	@When("^I navigates to \"([^\"]*)\" tab inside \"([^\"]*)\" inside \"([^\"]*)\" menu$")
	public void i_navigates_to_inside_menu(String subComponentTab, String subComponent, String componentType) {
		System.out.println("I navigates to " + subComponent + " inside " + componentType + " menu");
		navigationFlow.gotoComponentsItemPage(componentType, subComponent);
	}
}
