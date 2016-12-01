package com.morningstar.bdd.listener;

import java.util.Calendar;
import java.util.Date;

import org.testng.ISuite;

import com.morningstar.automation.base.core.listener.BaseSuiteListener;
import com.morningstar.automation.base.core.utils.Logger;
import com.morningstar.bdd.report.CucumberReport;
import com.morningstar.bdd.report.ReportSolution;

public class SuiteListener extends BaseSuiteListener {
	private static final Logger logger = Logger.getLogger(SuiteListener.class);

	private Date startDate = null;
	private Date endDate = null;

	@Override
	public void onStart(ISuite suite) {
		logger.info("onStart");
		this.startDate = Calendar.getInstance().getTime();
		CucumberReport.setUpExtentCucumberReport();
		logger.info("Excution startDate :" + startDate);
	}

	@Override
	public void onFinish(ISuite suite) {
		this.endDate = Calendar.getInstance().getTime();
		logger.info("Excution endDate :" + endDate);
		ReportSolution solution = new ReportSolution(suite, startDate, endDate);
		logger.info("generate report");
		solution.generateReport();
		logger.info("backup report");
		solution.backupReport();
		logger.info("send report");
		solution.sendEmail(solution.getContent());

	}
}
