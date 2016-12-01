package com.morningstar.bdd.core;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.morningstar.automation.base.core.AbstractTest;
import com.morningstar.automation.base.core.annotation.MorningstarAutomationAnnotation;
import com.morningstar.automation.base.core.beans.TestDataBean;
import com.morningstar.automation.base.core.beans.UserBean;
import com.morningstar.automation.base.core.configurations.Environment;
import com.morningstar.automation.base.core.testdata.TestDataUtil;
import com.morningstar.automation.base.core.utils.Logger;
import com.morningstar.automation.base.core.utils.SeleniumUtil;
import com.morningstar.automation.base.core.utils.TestObjectManager;
import com.morningstar.automation.base.core.utils.Util;
import com.morningstar.bdd.report.CucumberReport;
import com.morningstar.bdd.utils.UtilExtend;

import cucumber.api.testng.CucumberFeatureWrapper;

public abstract class AbstractRunnerBase {
	private static Logger logger = Logger.getLogger(AbstractTest.class);
	protected static final String DataProviderMethod = "features";
	protected Map<String, TestNGCucumberRunner> testNGCucumberRunnerMap;

	public AbstractRunnerBase(){
		testNGCucumberRunnerMap = new ConcurrentHashMap<String, TestNGCucumberRunner>();
	}
	
	public void feature(CucumberFeatureWrapper cucumberFeature, Method method) {
		testNGCucumberRunnerMap.get(method.getName()).runCucumber(cucumberFeature.getCucumberFeature());
	}
	
	@DataProvider
	public Object[][] features(Method method) {
		TestNGCucumberRunner testNGCucumberRunner = new TestNGCucumberRunner(this.getClass(), method);
		testNGCucumberRunnerMap.put(method.getName(), testNGCucumberRunner);
		return testNGCucumberRunner.provideFeatures();
	}
	
	@BeforeClass(alwaysRun = true)
    public void setUpClass() {
		CucumberReport.setUpExtentCucumberReport();
	}
	
	@AfterClass
    public void tearDownClass() throws Exception {
		for(TestNGCucumberRunner testNGCucumberRunner:testNGCucumberRunnerMap.values()) {
			testNGCucumberRunner.finish();
		}
    }
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method) {
		try {
			ContextManager.addCurrentMehodName(method.getName());
			logger.info("[SetUp]Test Case ID:" + method.getName() + "====BaseBeforeMethod start");

			logger.info("[SetUp]Test Case ID:" + method.getName() + "====current env: " + Util.getEnvStr());

			// Get browser info.
			String browserType = Util.getBrowserType();
			String browserVersion = Util.getBrowserVersion();
			String platform = Util.getPlatform();
			logger.info("[SetUp]Test Case ID:" + method.getName() + "====browserType: " + browserType + ", verison: "
					+ browserVersion + ", platform: " + platform);

			// Create driver by type
			logger.info("[SetUp]Test Case ID:" + method.getName() + "====Creating driver...");
			WebDriver driver = SeleniumUtil.createDriver(browserType, browserVersion, platform);
			logger.info("[SetUp]Test Case ID:" + method.getName() + "====Create driver successfully.");

			logger.info("[SetUp]Test Case ID:" + method.getName() + "====Add driver into TestObjectManager...");
			this.addDriver(driver);
			logger.info(
					"[SetUp]Test Case ID:" + method.getName() + "====Add driver into TestObjectManager successfully.");

			// log node to help to debug on Grid
			if (Util.isUseGrid()) {
				logger.info("[SetUp]Test Case ID:" + method.getName() + "===current node: "
						+ UtilExtend.GetNodeName(getCurrentDriver()));
			}

			String owner = this.getOwner(method);
			TestObjectManager.addOwner(method.getName(), owner);
			logger.info("[SetUp]Test Case ID:" + method.getName() + "====Add owner into TestObjectManager.");

			// Maximize browser window
			driver.manage().window().maximize();

			String homeURL = Environment.getHomePageUrl();
			logger.info("[SetUp]Test Case ID:" + method.getName() + "====Navigating to URL: " + homeURL);
			driver.get(homeURL);

			// if the URL is failed to forward, refresh the browser again
			while (driver.getCurrentUrl().contains("undefined")) {
				logger.info("[SetUp]Test Case ID:" + method.getName() + "====Re-Navigating to URL: " + homeURL);
				driver.get(homeURL);
			}

			logger.info(
					"[SetUp]Test Case ID:" + method.getName() + "====Navigate to URL: " + homeURL + " sucessfully.");

			logger.info("[SetUp]Test Case ID:" + method.getName() + "====Loading test data...");
			this.initTestData(method);
			logger.info("[SetUp]Test Case ID:" + method.getName() + "====Load test data successfully.");

			logger.info("[SetUp]Test Case ID:" + method.getName() + "====BaseBeforeMethod end");
		} catch (Exception ex) {
			logger.error("[SetUp]-{BaseBeforeMethod}-Test Case ID:" + method.getName() + "====" + ex.getMessage());
			logger.error("[SetUp]-{BaseBeforeMethod}-Test Case ID:" + method.getName()
					+ "====The stracktrace of this case====");
			ex.printStackTrace();
			Assert.fail("[SetUp]-{BaseBeforeMethod}-Test Case ID:" + method.getName() + "====ERROR:Fail to SetUp");
		}
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod(Method method) {
		//testNGCucumberRunnerMap.get(method.getName()).finish();
		logger.info("[TearDown]Test Case ID:" + method.getName() + "====BaseAfterMethod start");
		try {
			WebDriver driver = this.getCurrentDriver();
			logger.info("[TearDown]Test Case ID:" + method.getName() + "====URL: " + driver.getCurrentUrl());

			// Close windows one by one
			Set<String> handles = driver.getWindowHandles();
			for (int i = handles.size() - 1; i >= 0; i--) {
				driver.switchTo().window(handles.toArray()[i].toString());
				driver.close();
			}
			// closeBrowserSession();

			driver.quit();

			logger.info(
					"[TearDown]Test Case ID:" + method.getName() + "====All browsers quit successfully on Windows.");
		} catch (Exception ex) {
			logger.error("[TearDown]-{BaseAfterMethod}-Test Case ID:" + method.getName() + "====" + ex.getMessage());
			logger.error("[SetUp]-{BaseAfterMethod}-Test Case ID:" + method.getName()
					+ "====The stracktrace of this case====");
			ex.printStackTrace();
			logger.error(
					"[TearDown]-{BaseAfterMethod}-Test Case ID:" + method.getName() + "====ERROR:Fail to TearDown");
		}

		logger.info("[TearDown]Test Case ID:" + method.getName() + "====BaseAfterMethod end");
	}

	public String getOwner(Method method) {
		String result = "";
		MorningstarAutomationAnnotation annotation = method.getAnnotation(MorningstarAutomationAnnotation.class);
		if (annotation != null) {
			result = annotation.owner();
		}
		return result == null ? "" : result;
	}

	private void initTestData(Method method) {
		String testcaseId = method.getName();
		List<TestDataBean> data = TestDataUtil.getTestData(testcaseId);
		TestObjectManager.addTestData(method.getName(), data);
	}

	public UserBean getUser(Method method) {
		return TestObjectManager.getUser(method.getName());
	}

	public String getTestDataByKey(String testCaseId, String parameter) {
		List<TestDataBean> data = TestObjectManager.getTestData(testCaseId);
		String env = Environment.getEnvironmentBean().getType().toLowerCase();

		for (TestDataBean bean : data) {
			if (bean.getParameter().equals(parameter)) {
				Map<String, String> dataMap = bean.getDataMap();
				return dataMap.get(env.toLowerCase());
			}
		}
		return null;
	}

	public void addDriver(WebDriver driver) {
		ContextManager.addCurrentDriver(driver);
	}

	public WebDriver getCurrentDriver() {
		return ContextManager.getCurrentDriver();
	}

	public void closeBrowserSession() throws IOException {
		if (Environment.getDefaultPlatform().contains("WINDOWS")) {
			Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver*");
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer*");
			Runtime.getRuntime().exec("taskkill /F /IM Firefox.exe");
		} else {
			Runtime.getRuntime().exec("pkill chromedriver");
			Runtime.getRuntime().exec("pkill firefox");
		}
	}
	
}
