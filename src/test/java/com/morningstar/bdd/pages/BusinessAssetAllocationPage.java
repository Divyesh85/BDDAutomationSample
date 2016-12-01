package com.morningstar.bdd.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.morningstar.bdd.core.AbstractPageBase;

public class BusinessAssetAllocationPage extends AbstractPageBase implements IAssetAllocationPage {
	
	@FindBy(css="#mstarExample0 th[data-title=Asset]>span")
	private List<WebElement> example0AssetsColumnHeaderNameList;
	
	@FindBy(css="#mstarExample0 th[data-title='% Net']>span")
	private List<WebElement> example0NetColumnHeaderNameList;
	
	@FindBy(css="#mstarExample0 th[data-title='% Bmark']>span")
	private List<WebElement> example0BmarkColumnHeaderNameList;

	@Override
	public WebElement getAssetsColumnHeaderName() {
		return example0AssetsColumnHeaderNameList.get(0);
	}

	@Override
	public WebElement getPercentageNetColumnHeaderName() {
		return example0NetColumnHeaderNameList.get(0);
	}

	@Override
	public WebElement getPercentageBenchmarkColumnName() {
		return example0BmarkColumnHeaderNameList.get(0);
	}
	
}
