package com.morningstar.bdd.stepDefinitions.Xray;

import org.testng.Assert;

import com.morningstar.automation.base.core.utils.Logger;
import com.morningstar.bdd.flows.AssetAllocationFlow;
import com.morningstar.bdd.pages.BusinessAssetAllocationPage;
import com.morningstar.bdd.pages.FidelityAssetAllocationPage;

import cucumber.api.java.en.Then;

public class assetAllocationStepDefinitions {
	private static final Logger logger = new Logger(assetAllocationStepDefinitions.class);
	private AssetAllocationFlow assetAllocationFlow;

	public assetAllocationStepDefinitions() {
		assetAllocationFlow = new AssetAllocationFlow();
	}

	@Then("^Data Table for Asset Allocation on \"([^\"]*)\" should have \"([^\"]*)\" and \"([^\"]*)\" visible columns$")
	public void Data_Table_for_Asset_Allocation_should_have_and_visible_columns(String componentName, String col1, String col2) throws Throwable {
		logger.info("Verifying Asset Allocation component columns");
		this.setPage(componentName);
		Assert.assertTrue(assetAllocationFlow.verifyAssetsColumn(col1), "Error verifying Assets Column for Asset Allocation Breakdown");
		Assert.assertTrue(assetAllocationFlow.verifyPercentageNetColumn(col2), "Error verifying % Net Column for Asset Allocation Breakdown");
	}

	@Then("^Data Table for Asset Allocation on \"(.*?)\" should have \"(.*?)\" visible column$")
	public void data_Table_for_should_have_visible_columns(String componentName, String strColumnName) throws Throwable {
		this.setPage(componentName);
		Assert.assertTrue(assetAllocationFlow.verifyPercentageBenchMarkColumn(strColumnName), "Error verifying BenchMark Column for Asset Allocation");
	}
	
	public void setPage(String componentName){
		if(componentName.equalsIgnoreCase("Business-components"))
			assetAllocationFlow.setAssetAllocationPage(new BusinessAssetAllocationPage());
		else if(componentName.equalsIgnoreCase("FidelityXray"))
			assetAllocationFlow.setAssetAllocationPage(new FidelityAssetAllocationPage());
	}
}
