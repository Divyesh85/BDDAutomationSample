package com.morningstar.bdd.flows;

import org.openqa.selenium.WebElement;

import com.morningstar.automation.base.core.configurations.Environment;
import com.morningstar.automation.base.core.utils.SeleniumUtil;
import com.morningstar.bdd.core.AbstractFlowBase;
import com.morningstar.bdd.pages.NavigationPage;
import com.morningstar.bdd.utils.SeleniumUtilExtend;

public class NavigationFlow extends AbstractFlowBase {
	protected NavigationPage navigationPage;

	public NavigationFlow() {
		this.navigationPage = new NavigationPage();
	}

	public void gotoComponentsItemPage(String componentType, String subComponent) {
		if(!this.driver.getCurrentUrl().equalsIgnoreCase(Environment.getHomePageUrl()))
			this.driver.navigate().to(Environment.getHomePageUrl());
		navigationPage.getComponentsTypeBtn(componentType).click();
		SeleniumUtil.sleep(1);
		WebElement componentsItem = navigationPage.getComponentsItemBtn(componentType, subComponent);
		SeleniumUtil.scrollIntoView(driver, componentsItem);
		componentsItem.click();
		SeleniumUtilExtend.waitUntilAllAjaxRequestCompletes(driver);
	}
	
	public void gotoComponentsItemPageTab(String componentType, String subComponent, String subComponentTab) {
		this.gotoComponentsItemPage(componentType, subComponentTab);
		navigationPage.getComponentsItemTabBtn(subComponentTab).click();
		SeleniumUtilExtend.waitUntilAllAjaxRequestCompletes(driver);
	}
}
