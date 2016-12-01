package com.morningstar.bdd.report;

import java.io.File;

import com.morningstar.automation.base.core.utils.Util;
import com.morningstar.bdd.core.ExtentCucumberFormatter;

public class CucumberReport {
	private static boolean setUp = false;
	private static String reportFolder = "cucumber";
	private static String reportName = "report.html";
	private static String reportFolderPath;
	private static String reportFilePath;
	
	
	static {
		reportFolderPath = "output" + File.separator + reportFolder;
		reportFilePath = reportFolderPath + File.separator + reportName;
	}

	public static void setUpExtentCucumberReport() {
		if (!setUp) {
			ExtentCucumberFormatter.initiateExtentCucumberFormatter(new File(reportFilePath), true);
			setUp = true;
		}
	}
	
	public static String getReportFolder() {
		return reportFolder;
	}
	
	public static String getReportName() {
		return reportName;
	}

	public static String getReportFolderPath() {
		return reportFolderPath;
	}
	
	public static String getReportFilePath() {
		return reportFilePath;
	}

	
}
