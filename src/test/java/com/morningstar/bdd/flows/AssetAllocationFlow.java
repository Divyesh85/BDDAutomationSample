package com.morningstar.bdd.flows;

import com.morningstar.bdd.core.AbstractFlowBase;
import com.morningstar.bdd.pages.IAssetAllocationPage;

public class AssetAllocationFlow extends AbstractFlowBase {
	private IAssetAllocationPage aaPage = null;

	public AssetAllocationFlow() {
	}

	public AssetAllocationFlow(IAssetAllocationPage aaPage) {
		this.aaPage = aaPage;
	}

	public void setAssetAllocationPage(IAssetAllocationPage aaPage) {
		this.aaPage = aaPage;
	}

	public boolean verifyPercentageNetColumn(String strColumnName) {
		if (aaPage.getPercentageNetColumnHeaderName().getText().equalsIgnoreCase(strColumnName)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyAssetsColumn(String strColumnName) {
		if (aaPage.getAssetsColumnHeaderName().getText().equalsIgnoreCase(strColumnName)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyPercentageBenchMarkColumn(String strColumnName) {
		if (aaPage.getPercentageBenchmarkColumnName().getText().equalsIgnoreCase(strColumnName)) {
			return true;
		} else {
			return false;
		}
	}
}
