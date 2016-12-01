package com.morningstar.bdd.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.morningstar.automation.base.core.utils.SeleniumUtil;

public class SeleniumUtilExtend {
	
	public static WebElement getElementByText(List<WebElement> eleList, String name) {
		WebElement result = null;
		for (WebElement ele : eleList) {
			if (name.equals(ele.getText())) {
				result = ele;
				break;
			}
		}
		return result;
	}
	
	public static WebElement getElementByTextIgnoreCase(List<WebElement> eleList, String name) {
		WebElement result = null;
		for (WebElement ele : eleList) {
			if (name.equalsIgnoreCase(ele.getText())) {
				result = ele;
				break;
			}
		}
		return result;
	}
	
	public static WebElement getElementByAttributeValue(List<WebElement> eleList, String attributeName, String attributeValue) {
		WebElement result = null;
		for (WebElement item : eleList) {
			if (attributeValue.equals(item.getAttribute(attributeName))) {
				result = item;
				break;
			}
		}
		return result;
	}

	public static WebElement getDisplayElement(List<WebElement> eleList) {
		for (WebElement ele : eleList) {
			if (ele.isDisplayed()) {
				return ele;
			}
		}
		return null;
	}
	
	public static List<WebElement> getAllVisibleElements(List<WebElement> elements) {
		List<WebElement> result = new ArrayList<WebElement>();
		for (WebElement element : elements) {
			if (element.isDisplayed())
				result.add(element);
		}
		return result;
	}
	
	public static void waitUntilAllAjaxRequestCompletes(WebDriver driver) {
		SeleniumUtil.sleep(1);
		SeleniumUtil.waitUntilAllAjaxRequestCompletes(driver);
		SeleniumUtil.sleep(1);
	}
}
