package com.morningstar.bdd.runner;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.morningstar.automation.base.cons.Groups;
import com.morningstar.automation.base.core.annotation.MorningstarAutomationAnnotation;
import com.morningstar.bdd.core.AbstractRunnerBase;
import com.morningstar.bdd.core.ScenarioFilter;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;

@CucumberOptions (
	features = "src/test/resources/features/XRay/AssetAllocationComponent.feature",
	plugin = {"com.morningstar.bdd.core.ExtentCucumberFormatter"},
	glue = {"com.morningstar.bdd.stepDefinitions"}
)
public class AssetAllocation extends AbstractRunnerBase {
	
	@ScenarioFilter(tags="@component")
	@MorningstarAutomationAnnotation(owner = "cchang")
	@Test(groups = Groups.SMOKE, dataProvider = DataProviderMethod)
	public void BusinessComponentAssetAllocation(CucumberFeatureWrapper cucumberFeature, Method method) {
		super.feature(cucumberFeature, method);
	}
	
	@ScenarioFilter(name="Verify Asset Allocation component visible columns for Fidelity Xray 1")
	@MorningstarAutomationAnnotation(owner = "cchang")
	@Test(groups = Groups.SMOKE, dataProvider = DataProviderMethod)
	public void FidelityAssetAllocation1(CucumberFeatureWrapper cucumberFeature, Method method) {
		super.feature(cucumberFeature, method);
	}
	
	@ScenarioFilter(name="Verify Asset Allocation component visible columns for Fidelity Xray 2")
	@MorningstarAutomationAnnotation(owner = "cchang")
	@Test(groups = Groups.SMOKE, dataProvider = DataProviderMethod)
	public void FidelityAssetAllocation2(CucumberFeatureWrapper cucumberFeature, Method method) {
		super.feature(cucumberFeature, method);
	}
}