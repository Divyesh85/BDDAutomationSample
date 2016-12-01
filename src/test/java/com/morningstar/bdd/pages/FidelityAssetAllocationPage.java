package com.morningstar.bdd.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.morningstar.bdd.core.AbstractPageBase;

public class FidelityAssetAllocationPage extends AbstractPageBase implements IAssetAllocationPage {
	@FindBy(css="th[data-title=Asset]>span")
	private WebElement assetsColumnHeaderName;
	
	@FindBy(css="th[data-title='% Net']>span")
	private WebElement netColumnHeaderName;
	
	@FindBy(css="th[data-title='% Bmark']>span")
	private WebElement bmarkColumnHeaderName;
	
	@Override
	public WebElement getAssetsColumnHeaderName() {
		return assetsColumnHeaderName;
	}

	@Override
	public WebElement getPercentageNetColumnHeaderName() {
		return netColumnHeaderName;
	}

	@Override
	public WebElement getPercentageBenchmarkColumnName() {
		return bmarkColumnHeaderName;
	}

}
