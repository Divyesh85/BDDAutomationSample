package com.morningstar.bdd.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.morningstar.automation.base.core.utils.SeleniumUtil;
import com.morningstar.bdd.core.AbstractPageBase;
import com.morningstar.bdd.utils.SeleniumUtilExtend;

public class NavigationPage extends AbstractPageBase {
	
	@FindBy(css="span.selector")
	private List<WebElement> componentsTypes;
	
	@FindBy(css="ul>li>a")
	private List<WebElement> componentsTypeTabs;

	public WebElement getComponentsTypeBtn(String componentsTypeName) {
		return SeleniumUtilExtend.getElementByTextIgnoreCase(componentsTypes, componentsTypeName);
	}

	public List<WebElement> getgetComponentsItemBtnList(String componentsItemName) {
		String idName = componentsItemName.split("-")[0].toLowerCase() + "-list";
		return SeleniumUtil.waitForAllElementsPresent(driver, By.cssSelector("#" + idName + " > li > a > span"));
	}

	public WebElement getComponentsItemBtn(String componentsTypeName, String componentsItemName) {
		return SeleniumUtilExtend.getElementByTextIgnoreCase(this.getgetComponentsItemBtnList(componentsTypeName), componentsItemName);
	}
	
	public WebElement getComponentsItemTabBtn(String componentsItemTabName) {
		return SeleniumUtilExtend.getElementByTextIgnoreCase(componentsTypeTabs, componentsItemTabName);
	}
	
}
